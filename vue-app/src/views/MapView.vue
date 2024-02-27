<template>
  <div>
    <!-- 네이버 지도 표시 영역 -->
    <div id="map" style="width: 100%; height: 400px;"></div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      map: null,
      polylines: {}, // 날짜별 polyline 객체를 저장할 객체
      markers: {}, // 날짜별로 마커 객체를 저장할 객체
    };
  },
  mounted() {
    this.initMap();
    this.fetchAndPlaceMarkers();
  },
  methods: {
    initMap() {
      const mapOptions = {
        center: new naver.maps.LatLng(37.50339678708366, 126.94865788475123),
        zoom: 12,
      };
      this.map = new naver.maps.Map('map', mapOptions);
    },
    async fetchAndPlaceMarkers() {
      try {
        const response = await axios.get('http://localhost:8099/map');
        const list = response.data;

        list.forEach((item) => {
          const date = item.date; // 'YYYY.MM.DD' 형식 가정
          if (!this.markers[date]) {
            this.markers[date] = [];
          }

          const marker = new naver.maps.Marker({
            position: new naver.maps.LatLng(item.point_y, item.point_x),
            map: this.map,
          });

          this.markers[date].push(marker);

          // 마커 클릭 이벤트 리스너 추가
          naver.maps.Event.addListener(marker, 'click', () => {
            if (this.polylines[date]) {
              this.togglePolylineVisibility(date);
            } else {
              this.fetchAndDrawPolylineForDate(date);
            }
          });
        });
      } catch (error) {
        console.error('Error fetching markers:', error);
      }
    },
    async fetchAndDrawPolylineForDate(date) {
      // 날짜별로 필터링된 데이터가 필요
      // 이 예시에서는 모든 데이터를 이미 받아왔으므로, 서버에 다시 요청하지 않고 클라이언트 측에서 필터링
      const locations = this.filterLocationsByDate(date);

      const path = locations.map(location => new naver.maps.LatLng(location.point_y, location.point_x));

      const polyline = new naver.maps.Polyline({
        map: this.map,
        path: path,
        strokeColor: '#FF0000',
        strokeOpacity: 0.8,
        strokeWeight: 3,
      });

      this.polylines[date] = polyline;
    },
    filterLocationsByDate(date) {
      // `this.markers`에서 해당 날짜의 마커들을 찾아 위치 데이터를 반환
      return this.markers[date].map(marker => ({
        point_y: marker.getPosition().lat(),
        point_x: marker.getPosition().lng(),
      }));
    },
    togglePolylineVisibility(date) {
      const polyline = this.polylines[date];
      if (polyline) {
        const isVisible = polyline.getMap();
        polyline.setMap(isVisible ? null : this.map);
      }
    },
  },
};


</script>

 