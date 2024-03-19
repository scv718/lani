<template>
  <div style="display : grid">
    <!-- 네이버 지도 표시 영역 -->
    <div id="map" class="map"></div>

    <!-- 가로 슬라이드 패널 -->
    <div class="slidePanel">
      <div class="itemsContainer">
        <div v-for="(item, date) in listData" :key="date" class="item">
          <div v-for="(value, index) in item" :key="index" class="card" @mouseenter="pauseAnimation"
            @mouseleave="resumeAnimation">
            <img :src="getImagePath(value.filePath)" alt="Image" class="cardImg">
            <div class = 'textBox'>
            <p><strong></strong> {{ value.title }}</p>
            <p><strong></strong> {{ value.date }}</p>
            <p><strong></strong> {{ value.store }}</p>
          </div>
          </div>
        </div>
      </div>
    </div>
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
      listData: {},
      showDropdown: false,
    };
  },
  mounted() {
    this.initMap();
    this.fetchAndPlaceMarkers();
  },
  methods: {
    pauseAnimation() {
      // '.item' 클래스를 가진 모든 요소 선택
      const items = document.querySelectorAll('.item');
      // 각 항목의 애니메이션 일시 중지
      items.forEach(item => {
        item.style.animationPlayState = 'paused';
      });
    },
    resumeAnimation() {
      // '.item' 클래스를 가진 모든 요소 선택
      const items = document.querySelectorAll('.item');
      // 각 항목의 애니메이션 재생
      items.forEach(item => {
        item.style.animationPlayState = 'running';
      });
    },
    getImagePath(path) {
      // 이미지 파일명을 동적으로 생성하여 반환합니다.
      // return `C:\\workspace\\lani\\vue-app\\public\\img\\${path}`;
      return `/img/${path}`;
    },
    toggleDropdown() {
      this.showDropdown = !this.showDropdown;
      console.log(this.listData)
    },
    initMap() {
      const mapOptions = {
        center: new naver.maps.LatLng(37.5046658, 126.9387444),
        zoom: 10,
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
          if (!this.listData[date]) {
            this.listData[date] = [];
          }

          const marker = new naver.maps.Marker({
            position: new naver.maps.LatLng(item.point_y, item.point_x),
            map: this.map,
          });

          this.markers[date].push(marker);
          this.listData[date].push(item);

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

<style scoped>
body {
  height: 100%;
}

.map {
  width: 60%;
  height: 330px;
  position: relative;
  z-index: 100;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.slidePanel {
  background-color: #fff;
  /* 배경색 지정 */
  overflow: hidden;
  place-items: center;
  white-space: nowrap;
  /* 내용을 가로로 배열 */
  position: relative;
  /* top: 150px; */
  left: 0;
  right: 0;
  z-index: 103;
}

.itemsContainer {
  display: flex;
  /* Flexbox를 사용하여 항목을 가로로 배열 */
  overflow: hidden;
  /* 컨테이너 내부에서 항목이 넘칠 경우 가로 스크롤 허용 */
}


.item {
  display: flex;
  flex: 0 0 auto;
  /* 각 항목이 컨텐츠 크기에 맞게 유연하게 배열되도록 설정 */
  align-items: center;
  padding-top: 30px;
  perspective: 100px;
  animation: scroll 34s linear infinite;
}

.item:hover {
  animation-play-state: paused;
}

@keyframes scroll {
  0% {
    transform: translateX(0);
  }

  100% {
    transform: translateX(calc(-300px * 6));
  }
}


.card {
  width: 200px;
  height: 270px;
  margin-left: 120px;
  transition: transform 1s;
  border-radius: 10px;
  overflow: hidden;
  background-color: #FFB3BA;
}

.card:hover {
  transform: translateZ(18px);
}

.cardImg {
  padding-top: 10px;
  width: 100px;
  /* left: -50%; */
  /* transform: translateX(-50%); */
  
}
.textBox{
  text-align: center;
  margin: auto;
}
</style>