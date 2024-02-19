<template>
  <div>
    <!-- 네이버 지도 표시 영역 -->
    <div id="map" style="width: 100%; height: 400px;"></div>

    <!-- 마커 추가 버튼 -->
    <button @click="togglePopup">테스트</button>

    <!-- 팝업 -->
    <div v-if="isPopupOpen" class="popup">
      <!-- 팝업 내용 -->
      <input type="text" placeholder="네이버지도 링크 입력" v-model="naverMapLink">
      <br>
      <input type="text" placeholder="제목 입력" v-model="markerTitle">
      <button @click="sendMapData">마커 추가하기</button>
      <!-- 여기에 팝업 내용을 추가하세요 -->
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  mounted() {
    // 네이버 지도 초기화
    var mapOptions = {
      center: new naver.maps.LatLng(37.3595704, 127.105399),
      zoom: 10
    };
    var map = new naver.maps.Map('map', mapOptions);
  },
  data() {
    return {
      isPopupOpen: false,
      naverMapLink: '', // naverMapLink 속성 추가
      markerTitle: ''   // markerTitle 속성 추가
    };
  },
  methods: {
    togglePopup() {
      // 팝업을 열거나 닫습니다.
      this.isPopupOpen = !this.isPopupOpen;
    },
    async sendMapData() {
      // axios를 사용하여 HTTP POST 요청 보내기
      try {
        const response = await axios.post('http://localhost:8080/addMap', {
          naverMapLink: this.naverMapLink,
          markerTitle: this.markerTitle
        }, { withCredentials: true });
        console.log('응답:', response.data);
        this.isPopupOpen = false; // 요청이 성공하면 팝업을 닫습니다.
      } catch (error) {
        console.error('에러:', error);
        // 요청이 실패했을 때 에러 처리
      }
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
