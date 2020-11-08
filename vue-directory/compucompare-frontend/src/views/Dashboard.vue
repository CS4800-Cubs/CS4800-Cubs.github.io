<template>
	<div>
    <v-main class="blue lighten-5">

      <v-window
      v-model="window">
        <v-window-item>
          <v-container>
            <v-row>

              <v-col cols="3">
                <v-sheet rounded="lg">
                  <Filters/>

                </v-sheet>
              </v-col>

              <v-col>
                <v-sheet min-height="70vh" rounded="lg">
                  <ComputerList v-on:expandDevice="expandDevice"> </ComputerList>
                </v-sheet>
              </v-col>

              <v-col cols="3">
                <Cart/>
              </v-col>

            </v-row>
          </v-container>
        </v-window-item>

        <v-window-item>
          <v-container>
            <v-row>
              <v-col cols="1">
                <v-sheet rounded="lg" class="previous">
                  <v-btn
                    text
                    v-on:click="minimizeDevice"
                    v-ripple
                  >
                    <v-icon>mdi-chevron-left</v-icon>
                  </v-btn>
                </v-sheet>
              </v-col>
              <v-col>
                <v-sheet min-height="70vh" rounded="lg">
                  {{expandedDevice}}
                </v-sheet>
              </v-col>
              <v-col cols="3">
                <Cart/>
              </v-col>
            </v-row>
          </v-container>
        </v-window-item>
      </v-window>
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
    },
    data: ()=>({
      window: 0,
      length: 2,
      expandedDevice: [],
    }),
    methods: {
      expandDevice (computer) {
        this.window = this.window + 1 === this.length
          ? 0
          : this.window + 1
        this.expandedDevice = computer
      },
      minimizeDevice () {
        this.window = this.window - 1 < 0
          ? this.length - 1
          : this.window - 1
      }
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
  .previous{
    display: flex;
    justify-content: center;
  }
</style>