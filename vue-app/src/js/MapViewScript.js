import axios from 'axios';
import OtherComponent from '../views/MapView_modal.vue';


export default {
  components: {
    OtherComponent
  },
  data() {
    return {
      listData: {},
      mainImg: 0,
      prevImg: 0,
      nextImg: 1,
      showGallery: true,
      showTiles: false,
      isSliderActive: false,
      isModalOpen: false,
      imgObject: [],
      dateArray: []
    };
  },
  mounted() {
    this.fetchAndPlaceMarkers();
    // 이미지 초기화
    window.addEventListener('keyup', (e) => {
      if (e.keyCode === 37) { // 왼쪽 화살표
        this.scrollLeft();
      } else if (e.keyCode === 39) { // 오른쪽 화살표
        this.scrollRight();
      }
    });
  },
  methods: {
    openModal() {
      this.isModalOpen = true;
    },
    closeModal() {
      this.isModalOpen = false;
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
          this.listData[date].push(item);
        });
        this.initializeImages(); // 데이터 로드 후 이미지 초기화
      } catch (error) {
        console.error('Error fetching markers:', error);
      }
    },
    initializeImages() {
      this.imgObject = [];
      Object.values(this.listData).flat().forEach((item) => {
        if (item.main_img === 'true') {
          this.imgObject.push(item.filePath);
          console.log(item)
        }
        this.dateArray.push(item)
      });
      if (this.imgObject.length > 0) {
        this.prevImg = this.imgObject.length - 1;
        this.mainImg = 0;
        this.nextImg = this.imgObject.length > 1 ? 1 : 0;
      }
    },
    getImagePath(filePath) {
      console.log("getPath " + filePath)
      return `/img/${filePath}`;
    },
    backgroundStyle(img) {
      console.log("background "+img)
      return {
        background: `url(${this.getImagePath(img)})`,
        backgroundSize: 'contain'
      };
    },
    toggleState3() {
      this.isSliderActive = !this.isSliderActive;
      this.showGallery = !this.isSliderActive;
      this.showTiles = this.isSliderActive;

      const tilesView = document.getElementById("tilesView");
   tilesView.style.display = this.showTiles ? "flex" : "none";
    },
    scrollRight() {
      this.prevImg = this.mainImg;
      this.mainImg = this.nextImg;
      this.nextImg = (this.nextImg + 1) % this.imgObject.length;
    },
    scrollLeft() {
      this.nextImg = this.mainImg;
      this.mainImg = this.prevImg;
      this.prevImg = (this.prevImg - 1 + this.imgObject.length) % this.imgObject.length;
    },
    selectImage(index) {
      this.mainImg = index;
      this.showTiles = false;
      this.showGallery = true;
      this.isSliderActive = false;
    },
  },
};