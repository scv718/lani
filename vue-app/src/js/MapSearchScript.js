import axios from 'axios';

export default {
  data() {
    return {
      isPopupOpen: false,
      date: '',
      // location: '',
      pointX: '',
      poinyY: '',
      title: '',
    };
  },
  methods: {
    onFileChange(e) {
      const file = e.target.files[0];
      this.image = file;
    },
    togglePopup() {
      // 팝업 open close 
      this.isPopupOpen = !this.isPopupOpen;
    },
    async sendMapData() {
      // axios
      try {
        const {result:{ x, y}} = await this.convertAddressToCoordinates(this.location);
        const response = await axios.post('http://localhost:8099/addMap', {
          date: this.date,
          // location: this.location,
          point_x: x,
          point_y: y,
          title: this.title
        }, { withCredentials: true });
        console.log('응답:', response.data);
        this.isPopupOpen = false; // 요청 성공 시 팝업 close
      } catch (error) {
        console.error('에러:', error)
      }
    },
    convertAddressToCoordinates(address) {
      return new Promise((resolve, reject) => {
        naver.maps.Service.geocode({
          query: address
        }, (status, response) => {
          if (status !== naver.maps.Service.Status.OK) {
            return reject(new Error('주소를 찾을 수 없습니다.'));
          }

          var result = response.v2, // v2 API 응답
            item = result.addresses[0],
            point = { x: item.x, y: item.y }; // 변환된 좌표
            console.log(item);

          resolve({ status: 'OK', result: point });
        });
      });
    },
  }
}
