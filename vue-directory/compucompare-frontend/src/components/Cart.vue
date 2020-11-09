<template>
    <div>
        <v-list color="transparent">
          <transition name="fade" 
              v-for="computer in selected"
              :key="computer.id">
            <v-list-item >
              <v-list-item-content>
                <v-list-item-title
                small>
                  {{computer.brand}} - {{computer.model}}
                </v-list-item-title>
              </v-list-item-content>
              <v-btn
              small
              v-ripple
              v-on:click="removeFromCart(computer)"><v-icon >mdi-delete</v-icon> </v-btn>
            </v-list-item>
          </transition>

          <v-divider class="my-2"></v-divider>

         <v-list-item
           link
           color="blue lighten-4"
         >
           <v-list-item-content>
             <v-list-item-title>
               <v-btn
                 text
                 small
                 v-on:click="compareCart">
                 Compare Devices
               </v-btn> 
             </v-list-item-title>
           </v-list-item-content>

         </v-list-item>
        </v-list>
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
        },
        compareCart(){
            this.$emit('goToCompare');
        }
    }
}
</script>

<style scoped>
	.fade-enter-active, .fade-leave-active {
    transition: opacity .2s;
  }
  .fade-enter, .fade-leave-to  {
    opacity: 0;
  }
</style>