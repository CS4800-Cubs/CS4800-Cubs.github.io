<template>
	<div>
 <v-main class="blue lighten-5">
      <v-container>
        <v-row>
          <v-col cols="2">
            <v-sheet rounded="lg">
              <v-list color="transparent">
                <v-divider class="my-2"></v-divider>

                <v-list-item
                  link
                  color="blue lighten-4"
                >
                  <v-list-item-content>
                    <v-list-item-title>
                      <v-btn
                        text
                        small>
                        Apply Filter
                      </v-btn> 
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-sheet>
          </v-col>

          <v-col>
            <v-sheet
              min-height="70vh"
              rounded="lg"
            >
            <ComputerList/>
            </v-sheet>
          </v-col>

          <v-col cols="2">
            <v-sheet rounded="lg">
              <v-list color="transparent">
                <v-list-item
                  v-for="computer in selected"
                  :key="computer.id"
                >
                  <v-list-item-content>
                    <v-list-item-title>
                      {{computer.title}}
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>

                <v-divider class="my-2"></v-divider>

                <v-list-item
                  link
                  color="blue lighten-4"
                >
                  <v-list-item-content>
                    <v-list-item-title>
                      Compare Devices
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
	</div>
</template>

<script>
import ComputerList from "../components/ComputerList"

  export default {
    components: {
      ComputerList
    },
    computed: {
      selected(){
        return this.$store.state.selected
    },
  }
}
</script>

<style scoped>
	
</style>