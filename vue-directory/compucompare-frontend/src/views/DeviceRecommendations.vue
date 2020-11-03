<template>
	<div>
    <v-main class="blue lighten-5">
      <v-container>
        <v-row>
          <v-col cols="2">
            <v-sheet rounded="lg">
            </v-sheet>
          </v-col>

          <v-col>
            <v-sheet
              min-height="70vh"
              rounded="lg"
            >
            </v-sheet>
          </v-col>
          
          <v-col cols="3">
            <Cart/>
          </v-col>

        </v-row>
      </v-container>
    </v-main>
	</div>
</template>

<script>
import Cart from "../components/Cart"
export default{
	name: "DeviceRecommendations",
  components: {
    Cart
  }
}
</script>

<style scoped>
	
</style>

