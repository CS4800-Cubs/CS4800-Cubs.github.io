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
              <v-banner>
                <v-btn v-on:click="goToSurveyResults">
                View your survey results!
                <v-icon>
                mdi-laptop
                </v-icon>
                </v-btn>
              </v-banner>
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

                    v-on:click="minimizeDevice"
                    v-ripple
                  >
                    <v-icon>mdi-chevron-left</v-icon>
                  </v-btn>
                </v-sheet>
              </v-col>
              <v-col>
                <v-card min-height="70vh">
                  <AdvanceDetails :computer="expandedDevice" />
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
                  <div v-for="comparedComputer in comparisonResults" :key="comparedComputer.id">
                        <v-card
                          class="card"
                          outlined
                        >
                          <v-list-item three-line>
                            <v-list-item-content>
                              <div class="overline mb-4">
                                {{comparedComputer.brand}} - {{comparedComputer.model}}
                              </div>
                              <v-list-item-subtitle>{{comparedComputer.displayName}}</v-list-item-subtitle>
                            </v-list-item-content>
                    
                            <v-list-item-avatar
                              tile
                              size="80"
                            ><v-img contain :src="comparedComputer.thumbnailUrl"></v-img></v-list-item-avatar>
                            <v-btn
                              text
                              v-on:click="expandDeviceFromCompare(comparedComputer)"
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

                    v-on:click="goToCompare()"
                    v-ripple
                  >
                    <v-icon>mdi-chevron-left</v-icon>
                  </v-btn>
                </v-sheet>
              </v-col>
              <v-col>
                <v-card min-height="70vh">
                  <AdvanceDetails :computer="expandedDevice"/>
                </v-card>
              </v-col>
              <v-col cols="3">
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

                            v-on:click="returnFromSurveyResults()"
                            v-ripple
                          >
                            <v-icon>mdi-chevron-left</v-icon>
                          </v-btn>
                        </v-sheet>
                      </v-col>
                      <v-col>
                        <div v-for="item in results" :key="item.id">
                            <v-card
                            class="card"
                            outlined
                            >
                                <v-list-item three-line>
                                    <v-list-item-content>
                                        <div class="overline mb-4">
                                        {{item.brand}} - {{item.model}}
                                        </div>
                                    <!--
                                        <h3>
                                        {{item.title}}
                                        </h3>-->
                                        <v-list-item-subtitle>{{item.displayName}}</v-list-item-subtitle>
                                        </v-list-item-content>

                                        <v-list-item-avatar
                                        tile
                                        size="80"
                                        ><v-img contain :src="item.thumbnailUrl"></v-img></v-list-item-avatar>

                                        <v-btn
                                        text
                                        v-on:click="expandDevice(item)"
                                        >
                                        <v-icon>mdi-chevron-right</v-icon>
                                        </v-btn>
                                    </v-list-item>

                                    <v-card-actions>
                                        <v-btn
                                        outlined
                                        rounded
                                        text
                                        v-on:click="addToCompare(item)"
                                        small
                                        >
                                        Compare
                                        </v-btn>
                                </v-card-actions>
                            </v-card>
                        </div>
                      </v-col>
                      <v-col cols="3">
                                     <v-card class="mx-auto">
                                       <Cart v-on:goToCompare="goToCompare"></Cart>
                                     </v-card>
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
import AdvanceDetails from "../components/AdvanceDetails"


  export default {
    components: {
      ComputerList,
      Cart,
      Filters,
      AdvanceDetails
    },
    data: ()=>({
      window: 0,
      length: 2,
      expandedDevice: [],
    }),
    methods: {
      expandDevice (computer) {
        this.window = 1
        this.$store.state.windowPosition = 1
        this.expandedDevice = computer
      },
      minimizeDevice () {
        this.window = 0
        this.$store.state.windowPosition = 0
      },
      expandDeviceFromCompare(computer){
        this.window = 3
        this.$store.state.windowPosition = 3
        this.expandedDevice = computer
      },
      goToCompare() {
        this.window = 2
        this.$store.state.windowPosition = 2
      },
      returnFromCompare(){
        this.window = 0
        this.$store.state.windowPosition = 0
      },
      returnFromSurveyResults(){
        this.window = 0
        this.$store.state.windowPosition = 0
      },
      goToSurveyResults() {
      this.window = 4
      this.$store.state.windowPosition = 4
      },
      addToCompare(computer){
        this.$store.dispatch('addToCompare', computer)
      },
      emitEvent: function (computer) {
        this.$emit('expandDevice', computer);
      }
    },
    computed: {
      computers(){
        return this.$store.state.selected
      },
      results() {
        return this.$store.state.results
      },
      comparisonResults(){
        return this.$store.state.compareResults
      }
    },
    created(){
        this.$store.dispatch("loadSurveyResults")
        this.window = 0
       //this.window = this.$store.state.windowPosition
       }}
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