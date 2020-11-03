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
            <h4>
              Results...
            </h4>
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

  export default {
    components: {
      ComputerList,
      Cart
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