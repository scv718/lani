<template>
  <nav>
    <router-link to="/" class="mainBTN">LANI & JJINKO</router-link>
    <span v-if="!isAboutPage"> | </span>
    <router-link v-if="!isAboutPage" to="/about">Map</router-link>
    <router-link v-if="!isAboutPage" to="/add">add</router-link>
  </nav>
  <router-view/>
</template>

<script>
export default {
  computed: {
    isAboutPage() {
      return this.$route.path === '/about';
    }
  }
}
</script>

<style>
@font-face {
    font-family: 'iceJaram-Rg';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2307-2@1.0/iceJaram-Rg.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
.mainBTN{
  font-size: 30px;
}
#app {
  font-family: 'iceJaram-Rg';
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  /* padding: 30px; */

background-color: rgba(31, 41, 55, var(--tw-bg-opacity));


}

nav a {
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
