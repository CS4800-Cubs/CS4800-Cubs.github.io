<template>
    <div>
      <v-sheet rounded="lg">
        <v-list color="transparent">
          <v-list-item
            v-for="computer in selected"
            :key="computer.id"
          >
            <v-list-item-content>
              <v-list-item-title
              small>
                {{computer.brand}} - {{computer.model}}
              </v-list-item-title>
            </v-list-item-content>
            <v-btn
            v-on:click="removeFromCart(computer)"> - </v-btn>
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
    </div>
</template>

<script>
export default{
    name: "Cart",
    computed: {
      selected(){
        return this.$store.state.selected
      }
    },
    created(){
        this.$store.dispatch("loadCart")
    },
    methods: {
        removeFromCart(computer){
            this.$store.dispatch("removeFromCart", computer)
        }
    }
}
</script>

<style scoped>
	
</style>