<template>
  <div id="app">
    <div id="nav">
      <router-link to="/computers">Computers</router-link> |
      <router-link to="/about">About</router-link>
    </div>
    <router-view/>
  </div>
</template>

<style>
* {
  font-family: 'Nunito', sans-serif;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

</style>
