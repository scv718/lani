<template>
  <div class = "candleMain"> 
  <div class="candle" @click="extinguishCandle" :class="{ extinguished: extinguished }">
    <div class="flame">
      <div class="shadows"></div>
      <div class="top"></div>
      <div class="middle"></div>
      <div class="bottom"></div>
    </div>
    <div class="wick"></div>
    <div class="wax"></div>
  </div>
</div>
</template>

<script>
export default {
  data() {
    return {
      extinguished: false
    };
  },
  methods: {
    extinguishCandle() {
      this.extinguished = true;
      setTimeout(() => {
        // 애니메이션이 끝나면 페이지 이동
        this.$router.push('/main');
      }, 3000); // 애니메이션의 지속 시간에 맞추어 설정 (여기서는 3초로 가정)
    }
  }
};
</script>

<style scoped lang="scss">

@keyframes extinguish-flame {
  0% {
    opacity: 1;
    transform: translateY(0) rotate(-2eg);
  }
  50% {
    opacity: 0.5;
    transform: translateY(-5px) rotate(0deg);
  }
  100% {
    opacity: 0;
    transform: translateY(-10px) rotate(2deg);
  }
}

.candle.extinguished {
  animation: none; /* 애니메이션 제거 */

}

.candle.extinguished .wick:before {
  display: none; /* 애니메이션 제거 */
}

.candle.extinguished .flame {
  animation: none; /* 애니메이션 제거 */
  opacity: 0; /* 투명도 조정 */
  transition: opacity 1s ease; /* 투명도 변경 애니메이션 설정 */
}

.candleMain {
  height: 100vh; /* body 전체 높이 */
  width: 100%; /* body 전체 너비 */
  background-color: #23161a; /* 배경색 */
  display: flex; /* flexbox를 사용하여 자식 요소를 정렬 */
  justify-content: center; /* 가로 방향 가운데 정렬 */
  align-items: center; /* 세로 방향 가운데 정렬 */
}
$flame-height: 150px;
$wick-height: 50px;
$orange: #ff6a00;
$orange-yellow: #ff9224;
$dark: #2c2b39;
$blue-dark: #30537d;
$blue-light: #76daff;
$yellow: #fbf348;
$yellow-grey: #58523a;

.candle{
  width: 55px;
  margin: 30px auto 0 auto;
  position: relative;
  height: 80%;
  align-self: flex-end;
  animation: blink .1s infinite;
}
.wick{
  position: absolute;
  width: 6px;
  height: $wick-height;
  background: #23161a;
  top: $flame-height - 0.8*$wick-height;
  left: 50%;
  transform: translateX(-50%) skewX(2deg);
  border-radius: 10%;
  box-shadow: 0 0 2px 0px black;
  &:before{
    content: '';
    position: absolute;
    width: 0;
    left: 50%;
    height: 10px;
    box-shadow: 
      0 -14px 10px 8px white, 
      0 -10px 10px 8px rgba(255,215,0,0.7), //gold
      0 -3px 10px 8px rgba(255,106,0,0.7),  //orange
      0 6px 3px 4px black;
  }
  &:after{
    content: '';
    position: absolute;
    left: 50%;
    bottom:0;
    width: 0px;
    height: 0px;
    box-shadow: 
      0 5px 2px 3px gold,
      0 20px 2px 14px gold,
      0 -6px 4px 5px rgba(98, 33, 27, 0.8),  //dark orange
      0 0px 1px 4px rgba(255, 106, 0, 0.7),  //orange
      0 0px 3px 4px $orange,
      0 5px 3px 4px gold;
  }
}
.flame{
  width: 20px;
  height: $flame-height;
  margin: 0px auto;
  position: relative;
  animation: move 3s infinite, move-left 3s infinite;
  transform-origin: 50% 90%;
  .top{
    width: 20px;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    background: white; 
    border-top-left-radius: 500%;
    border-bottom-left-radius: 50px;
    border-top-right-radius: 500%;
    border-bottom-right-radius: 50px;
    transform: skewY(-10deg);
    box-shadow: 
      0 0px 0px 3px white,
      0 -20px 1px 4px white, 
      0 -25px 2px 3px gold, 
      0 -30px 5px 4px $orange,  //top
      0 0px 150px 10px $orange, //global
      0 -10px 2px 4px white,
      0 -5px 3px 3px white;
     animation: flame-up 4s infinite;
  }
  .shadows{
    position: absolute;
    left: 50%;
    top: 0;
    width: 1px;
    height: 60px;
    border-radius: 50%;
    box-shadow: 
      0 5px 20px 15px gold, 
      0 0px 100px 20px $orange,
      0 15px 50px 15px $orange,
      5px 30px 5px 13px $orange,
      5px 50px 5px 13px $orange,
      0 75px 50px 30px black;
  }
  .bottom{
    transform: scale(0.9);
    position: absolute;
    bottom: 6px;
    left: 9px;
    width: 1px;
    height: 8px;
    border-radius: 1%;
    background: $dark;
    box-shadow: 
      0 6px 10px 12px rgba(60, 76, 125, 0.3), //dark blue
      0 0px 4px 8px $dark,
      0 -12px 10px 8px rgba(255,106,0, 0.5),  //orange
      0 5px 7px 12px $dark,
      0 -3px 10px 12px $dark,
      5px -10px 10px 5px red, 
      0 -15px 10px 10px gold, 
      5px -25px 10px 5px gold,
      0 2px 5px 10px $blue-dark,
      0 -2px 2px 14px $blue-light,
      0 2px 10px 12px $blue-light;
  }
}
.wax{
  position: relative;
  top: 15px;
  width: 100%;
  height: 80%;
  background: $orange-yellow;
  background: -moz-linear-gradient(top, $orange-yellow 0px, $orange-yellow 20px, $yellow-grey 50px); /* FF3.6-15 */
  background: -webkit-linear-gradient(top, $orange-yellow 0px, $orange-yellow 20px, $yellow-grey 50px); /* Chrome10-25,Safari5.1-6 */
  background: linear-gradient(to bottom, $orange-yellow 0px, $orange-yellow 20px, $yellow-grey 50px); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ff9224', endColorstr='#58523a',GradientType=0 ); /* IE6-9 */
  
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  box-shadow: 
    inset 0 7px 12px -2px $yellow, 
    inset 0 9px 57px -3px rgba(255,0,0,0.4), 
    inset 0 -5px 8px 2px black,
    0 0 3px 0px $orange;
}

@keyframes move{
  0%{ transform: skewX(2deg) skewY(5deg)}
  50%{ transform: skewX(-2deg) skewY(-0deg)}
  100%{ transform: skewX(2deg) skewY(5deg)}
}
@keyframes move-left{
  50%{transform: skewX(3deg);}
}
@keyframes flame-up{
  50% { box-shadow:
      0 0px 0px 3px white,
      0 -38px 1px 2px white, 
      0 -41px 2px 3px gold, 
      0 -50px 5px 4px $orange,  //orange top
      0 0px 150px 10px $orange, //orange global
      0 -10px 2px 4px white,
      0 -5px 3px 3px white;
  }
}
@keyframes blink{
  50% {opacity: 0.95;}
}
</style>