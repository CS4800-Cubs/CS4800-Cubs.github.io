<template>
  <v-app id="inspire">
    <TopNavigation/>
    <router-view/>
  </v-app>
</template>

<script>
import TopNavigation from "./components/TopNavigation"

  export default {
    components: {
      TopNavigation
    },
  }


</script>
<style scoped>

.theme--light.v-application {
  background-color: #e3f2fd;
}
</style>