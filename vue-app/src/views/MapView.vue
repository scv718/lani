<template>
  <div>
    <!-- 네이버 지도 표시 영역 -->
    <div id="map" style="width: 100%; height: 400px;"></div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  mounted() {
    // 네이버 지도 초기화
    var mapOptions = {
      center: new naver.maps.LatLng(37.50339678708366, 126.94865788475123),
      zoom: 12,
      mapTypes: new naver.maps.MapTypeRegistry({
        'normal': naver.maps.NaverStyleMapTypeOptions.getNormalMap(
          {
            overlayType: 'bg.lko.ol' // 네이버 지도 오버레이 부분
          }
        )
      })
    };
    this.map = new naver.maps.Map('map', mapOptions);

    // 페이지가 로드될 때 리스트를 가져와서 마커 생성
    this.fetchAndPlaceMarkers();
  },
  data() {
    return {
      map: null
    };
  },
  methods: {
    async fetchAndPlaceMarkers() {
      try {
        // Axios를 사용하여 서버에서 리스트 가져오기
        const response = await axios.get('http://localhost:8099/map');
        const list = response.data; // 가져온 리스트
        // 리스트의 각 항목에 대해 마커 생성
        list.forEach(item => {
          const marker = new naver.maps.Marker({
            position: new naver.maps.LatLng(item.point_y, item.point_x),
            map: this.map
          });
          // 마커에 대한 이벤트 리스너 등을 설정할 수 있음
          this.fetchAndDrawPolyline();
        });
      } catch (error) {
        console.error('리스트 가져오기 에러:', error);
      }
    },
    // fetchAndPlaceMarkers 메소드 뒤에 추가
    async fetchAndDrawPolyline() {
      try {
        // Axios를 사용하여 서버에서 리스트 가져오기
        const response = await axios.get('http://localhost:8099/map');
        const list = response.data; // 가져온 리스트

        // 서로 연결될 선의 좌표를 담을 배열
        const polylinePositions = [];

        // 리스트의 각 항목에 대해 위치 정보를 배열에 추가
        list.forEach(item => {
          const position = new naver.maps.LatLng(item.point_y, item.point_x);
          polylinePositions.push(position);
        });

        // Polyline 생성
        const polyline = new naver.maps.Polyline({
          map: this.map, // 지도 객체
          path: polylinePositions, // Polyline을 이루는 위치 정보
          strokeColor: '#FF0000', // 선 색상
          strokeWeight: 2, // 선 두께
          strokeOpacity: 0.7 // 선 투명도
        });
      } catch (error) {
        console.error('위치 정보 가져오기 에러:', error);
      }
    },
    // 테스트용
    createFixedMarker() {
      const markerOptions = {
        position: new naver.maps.LatLng(37.50339678708366, 126.94865788475123),
        map: this.map
      };
      const marker = new naver.maps.Marker(markerOptions);
    }
  }
}
</script>

<style scoped>
/* 팝업 스타일 */
.popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  z-index: 9999;
}
</style>
// Polyline을 이루는 위치 정보 배열에 추가
polylinePositions.push(position);
});

// Polyline 생성
const polyline = new naver.maps.Polyline({
map: this.map, // 지도 객체
path: polylinePositions, // Polyline을 이루는 위치 정보
strokeColor: '#FF0000', // 선 색상
strokeWeight: 2, // 선 두께
strokeOpacity: 0.7 // 선 투명도
});
} catch (error) {
console.error('위치 정보 가져오기 에러:', error);
}