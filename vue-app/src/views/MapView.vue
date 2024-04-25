<template>
  <div class="container">
    <div class="main_container">
      <div class="card-container"> <!-- 1. 카드 컨테이너 추가 -->
        <div v-for="(item, date) in listData" :key="date">
          <div v-for="(value, index) in item" :key="index">
            <!-- main_img가 true인 경우에만 backdrop-item 클래스가 적용된 div 요소를 렌더링 -->
            <div v-if="value.main_img === 'true'" class="backdrop-item">
              <img :src="getImagePath(value.filePath)" alt="Image" class="cardImg">
              <div class='textBox'>
                <p><strong></strong> {{ value.title }}</p>
                <p><strong></strong> {{ value.date }}</p>
                <p><strong></strong> {{ value.store }}</p>
              </div>
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
      listData: {},
    };
  },
  mounted() {
    this.fetchAndPlaceMarkers();
  },
  methods: {
    getImagePath(path) {
      // 이미지 파일명을 동적으로 생성하여 반환합니다.
      // return `C:\\workspace\\lani\\vue-app\\public\\img\\${path}`;
      return `/img/${path}`;
    },
    async fetchAndPlaceMarkers() {
      try {
        const response = await axios.get('http://localhost:8099/map');
        const list = response.data;

        list.forEach((item) => {
          const date = item.date; // 'YYYY.MM.DD' 형식 가정
   
          if (!this.listData[date]) {
            this.listData[date] = [];
          }

          console.log(item);

          this.listData[date].push(item);

        });
      } catch (error) {
        console.error('Error fetching markers:', error);
      }
    }
  },
}

</script>

<style scoped>
body {
  height: 100%;
}

.container {
  position: relative;
  min-height: 100vh;
  /* 이 부분을 추가합니다 */
  display: flex;
  justify-content: center;
  flex-direction: column;
  /* 자식 요소들을 세로로 정렬합니다 */
  background-color: #fff;
  border-bottom: 1px solid #e2e8f0;
  --tw-bg-opacity: 1;
  /* background-color: #800020; */
  background-color: rgba(31, 41, 55, var(--tw-bg-opacity));
}

.main_container{
  display: flex;
  position: absolute;
  width: 80%;
  height: 100%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding-left: 5%;
}

.card-container {
  display: flex; /* 가로로 요소를 나란히 정렬합니다 */
  flex-wrap: wrap; /* 화면 너비를 벗어나면 자동으로 줄바꿈합니다 */
  justify-content: space-around; /* 요소들을 가로 방향으로 중앙 정렬합니다 */
  margin-bottom: 20px; /* 카드 간의 간격을 조절합니다 */
}


.backdrop-item {
    margin-top: 20px;
    margin-right: 100px;
    width: 260px;
    height: 300px;
    -webkit-backdrop-filter: blur(10px);
    backdrop-filter: blur(20px);
    background-color: rgba(255, 255, 255, .36); /* 밝게 보이도록 배경색 추가 */
    border-radius: 30px; /* 둥근 테두리 */
    box-shadow: 0 6px 20px -15px #000; /* 그림자 효과 */
    border-width: 1px 1px 0 0; /* 입체감 흰색 테두리 */
    border-color: #fff;
    border-style: solid;
}
.cardImg {
  padding-top: 10px;
  width: 100px;
  /* left: -50%; */
  /* transform: translateX(-50%); */
  
}

.textBox{
  font-size: 20px;
  text-align: center;
  margin: auto;
}



</style>
