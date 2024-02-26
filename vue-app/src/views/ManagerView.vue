<template>
    <div>
      <!-- 네이버 지도 표시 영역 -->
      <div id="map" style="width: 100%; height: 400px;"></div>
  
      <!-- 마커 추가 버튼 -->
      <button @click="togglePopup">테스트</button>
  
      <!-- 팝업 -->
      <div v-if="isPopupOpen" class="popup">
        <!-- 팝업 내용 -->
        <input type="text" placeholder="날짜 입력" v-model="date">
  
        <input type="text" placeholder="주소 입력" v-model="location">
  
        <input type="text" placeholder="이름 입력" v-model="title">
        <input type="file" @change="onFileChange" accept="image/*">
        <button @click="sendMapData">마커 추가하기</button>
        <!-- 여기에 팝업 내용을 추가하세요 -->
      </div>
    </div>
  </template>

<script src="../js/MapSearchScript"></script>

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
