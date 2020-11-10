<template>
	<div>
    <v-main class="blue lighten-5">

      <v-window
      v-model="window">
        <v-window-item>
          <v-container>
            <v-row>

              <v-col cols="3">
                <v-card>
                  <Filters/>

                </v-card>
              </v-col>

              <v-col>
                <v-card min-height="70vh">
                  <ComputerList v-on:expandDevice="expandDevice"> </ComputerList>
                </v-card>
              </v-col>

              <v-col cols="3">
                <v-card class="mx-auto">
                  <Cart v-on:goToCompare="goToCompare"></Cart>
                </v-card>
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
                    flat  
                    v-on:click="minimizeDevice"
                    v-ripple
                  >
                    <v-icon>mdi-chevron-left</v-icon>
                  </v-btn>
                </v-sheet>
              </v-col>
              <v-col>
                <v-card min-height="70vh">
                  {{expandedDevice}}
                </v-card>
              </v-col>
              <v-col cols="3">
                <v-card class="mx-auto">
                  <Cart v-on:goToCompare="goToCompare"></Cart>
                </v-card>
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
                    v-on:click="returnFromCompare"
                    v-ripple
                  >
                    <v-icon>mdi-chevron-left</v-icon>
                  </v-btn>
                </v-sheet>
              </v-col>
              <v-col>
                <v-sheet min-height="70vh" rounded="lg">
<div v-for="computer in computers" :key="computer.id">
      <v-card
        class="card"
        outlined
      >
        <v-list-item three-line>
          <v-list-item-content>
            <div class="overline mb-4">
              {{computer.brand}} - {{computer.model}}
            </div>
            <!--
            <h3>
              {{computer.title}}
            </h3>-->
            <v-list-item-subtitle>Specifications</v-list-item-subtitle>
          </v-list-item-content>
  
          <v-list-item-avatar
            tile
            size="80"
            color="grey"
          ></v-list-item-avatar>
          <v-btn
            text
            v-on:click="expandDeviceFromCompare(computer)"
          >
            <v-icon>mdi-chevron-right</v-icon>
          </v-btn>
        </v-list-item>
      </v-card>
    </div>
                </v-sheet>
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
                    flat  
                    v-on:click="goToCompare()"
                    v-ripple
                  >
                    <v-icon>mdi-chevron-left</v-icon>
                  </v-btn>
                </v-sheet>
              </v-col>
              <v-col>
                <v-card min-height="70vh">
                  {{expandedDevice}}
                </v-card>
              </v-col>
              <v-col cols="3">
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
        this.window = 1
        this.expandedDevice = computer
      },
      minimizeDevice () {
        this.window = 0
      },
      expandDeviceFromCompare(computer){
        this.window = 3
        this.expandedDevice = computer
      },
      goToCompare() {
        this.window = 2
      },
      returnFromCompare(){
        this.window = 0
      }
    },
    computed: {
      computers(){
        return this.$store.state.selected
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