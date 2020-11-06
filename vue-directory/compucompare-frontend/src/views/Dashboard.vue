<template>
	<div>
 <v-main class="blue lighten-5">
      <v-container>
        <v-row>

          <v-col cols="3">
            <v-sheet rounded="lg">
              <Filters/>
            </v-sheet>
          </v-col>

          <v-col>
            <v-sheet min-height="70vh" rounded="lg">
              <ComputerList/>
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
import ComputerList from "../components/ComputerList"
import Cart from "../components/Cart"
import Filters from "../components/Filters"

  export default {
    components: {
      ComputerList,
      Cart,
      Filters
    }

  }
</script>

<style scoped>
	h4{
    padding-left: 15px;
    padding-top: 5px;
    color: gray;
    padding-bottom: 0; 
  }
</style>