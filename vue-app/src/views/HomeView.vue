<template>
  <div class="container">
    
      <main class="main-content">
        <div class="center-title">
          <img src="/mainImg/main_title.png" class="mainTitleImg">
          <h1 class="main-title">우리들의 발자취</h1>
        </div>

        <img src="/mainImg/heart_shadow.png" @click="redirectToMap">

        <p class="sub-text">

        </p>

        <div class="description-container">
        <div class="item" id="itemOne">
          <div class="polaroid"><img src="/mainImg/mainImg_1.jpg">
            <div class="caption">By Joshua Sortino</div>
          </div>
        </div>

        <div class="item" id="itemTwo">
          <div class="polaroid"><img src="/mainImg/mainImg_2.jpg">
            <div class="caption">By Cole Patrick</div>
          </div>
        </div>

        <div class="item" id="itemThree">
          <div class="polaroid"><img src="/mainImg/mainImg_3.jpg">
            <div class="caption">By Cole Patrick</div>
          </div>
        </div>

        <div class="item" id="itemThree">
          <div class="polaroid"><img src="/mainImg/manImg_6_cut2.jpg">
            <div class="caption">By Cole Patrick</div>
          </div>
        </div>
      </div>
        
      </main>

    <!-- <footer class="footer">
      <div class="footer-container">
      </div>
    </footer> -->
  </div>
</template>

<script>
export default {
  name: 'MainPage',
  methods: {
    redirectToMap() {
      this.$router.push('/about');
    }
  }
};
</script>

<style scoped>
body,
html {
  margin: 0;
  height: 100%;
}


.center-title {
  width: 40%;
  /* 너비 설정 */
  margin: 0 auto;
  /* 상하 마진 0, 좌우 마진 auto로 중앙 정렬 */
  display: flex;
  flex-direction: column;
  /* 자식 요소들을 세로로 정렬 */
  align-items: center;
  /* 가운데 정렬 */
  text-align: center;
  /* 텍스트 중앙 정렬 */
}





.container {
  min-height: 100vh;
  /* 이 부분을 추가합니다 */
  display: flex;
  flex-direction: column;
  /* 자식 요소들을 세로로 정렬합니다 */
  background-color: #fff;
  border-bottom: 1px solid #e2e8f0;
  --tw-bg-opacity: 1;
  background-color: rgba(31, 41, 55, var(--tw-bg-opacity));
  /* background-color: #97565e; */
}

/* 나머지 스타일은 유지합니다 */
.header {
  padding: 1rem 0;
}

.nav-container {
  display: flex;
  justify-content: space-between;
  padding: 0 1rem;
}

.navigation .nav-link {
  margin-right: 1rem;
  font-weight: 500;
  color: transparent;
  border-bottom: 2px solid transparent;
  transition: color 0.3s;
}

.navigation .nav-link:hover {
  border-color: #1f2937;
}

.main-content {
  flex-grow: 1;
  /* 메인 콘텐츠가 남은 공간을 모두 차지하도록 합니다 */
  padding: 1.5rem 0;
}

.mainSelection {
  width: 50%;
  border: 3px solid #e2e8f0;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
}

.text-container {
  text-align: center;
}

.text-section .welcome-text {
  color: #6b7280;
  font-weight: 600;
  font-size: 30px;
}

.sub-text {
  font-size: 30px;
  color: #6b7280;
}

.text-section .main-title {
  color: #e2e8f0;
  /* font-size: 60px; */
  /* font-weight: 800; */
}

.description-container .description-text {
  color: #6b7280;
}

.footer {
  padding: 3rem 0;
}

.footer-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
}

.footer-links {
  display: flex;
  gap: 1rem;
}

.footer-link {
  font-weight: 500;
}

.socials-avatar-container .avatar-link .avatar-image {
  border-radius: 9999px;
  aspect-ratio: 1;
  object-fit: cover;
}

.polaroid {
  background: #fff;
  padding: 1rem;
  box-shadow: 0 0.25rem 1rem rgba(0, 0, 0, 0.2);
}

.caption {
  font-size: 1.125rem;
  text-align: center;
  line-height: 2em;
}

.polaroid img {
  width: 100%;
}

.item {
  width: 25%;
  display: inline-block;
  margin-top: 2rem;
  filter: grayscale(100%);
}

.item .polaroid:before {
  content: '';
  position: absolute;
  z-index: -1;
  transition: all 0.35s;
}

.item:nth-of-type(4n+1) {
  transform: scale(0.8, 0.8) rotate(5deg);
  transition: all 0.35s;
}

.item:nth-of-type(4n+1) .polaroid:before {
  transform: rotate(6deg);
  height: 20%;
  width: 47%;
  bottom: 30px;
  right: 12px;
  box-shadow: 0 2.1rem 2rem rgba(0, 0, 0, 0.4);
}

.item:nth-of-type(4n+2) {
  transform: scale(0.8, 0.8) rotate(-5deg);
  transition: all 0.35s;
}

.item:nth-of-type(4n+2) .polaroid:before {
  transform: rotate(-6deg);
  height: 20%;
  width: 47%;
  bottom: 30px;
  left: 12px;
  box-shadow: 0 2.1rem 2rem rgba(0, 0, 0, 0.4);
}

.item:nth-of-type(4n+4) {
  transform: scale(0.8, 0.8) rotate(3deg);
  transition: all 0.35s;
}

.item:nth-of-type(4n+4) .polaroid:before {
  transform: rotate(4deg);
  height: 20%;
  width: 47%;
  bottom: 30px;
  right: 12px;
  box-shadow: 0 2.1rem 2rem rgba(0, 0, 0, 0.3);
}

.item:nth-of-type(4n+3) {
  transform: scale(0.8, 0.8) rotate(-3deg);
  transition: all 0.35s;
}

.item:nth-of-type(4n+3) .polaroid:before {
  transform: rotate(-4deg);
  height: 20%;
  width: 47%;
  bottom: 30px;
  left: 12px;
  box-shadow: 0 2.1rem 2rem rgba(0, 0, 0, 0.3);
}

.item:hover {
  filter: none;
  transform: scale(1, 1) rotate(0deg) !important;
  transition: all 0.35s;
}

.item:hover .polaroid:before {
  content: '';
  position: absolute;
  z-index: -1;
  transform: rotate(0deg);
  height: 90%;
  width: 90%;
  bottom: 0%;
  right: 5%;
  box-shadow: 0 1rem 3rem rgba(0, 0, 0, 0.2);
  transition: all 0.35s;
}
</style>
