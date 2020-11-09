<template>
   <div>
      <v-list color="transparent">
         <h4>Filters</h4>
         <v-list-item class="small">
            <v-list-item-content>
               <v-list-item-title>Computer Type</v-list-item-title>
               <v-radio-group v-model="selections.computerTypeSelected" class="group">
                  <v-radio v-for="option in computerTypeOptions" :key="option" :label="option" :value="option" class="selection"></v-radio>
               </v-radio-group>
            </v-list-item-content>
         </v-list-item>

         <v-list-item class="small">
            <v-list-item-content>
               <v-list-item-title>Computer Brand</v-list-item-title>
                  <v-checkbox 
                  v-for="option in computerBrandOptions" 
                  :key="option" 
                  :label="option" 
                  :value="option" 
                  class="selection" 
                  v-model="selections.computerBrandSelected"></v-checkbox>
            </v-list-item-content>
         </v-list-item>

            
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
                 v-on:click="applyFilters">
                 Apply Filter
               </v-btn> 
             </v-list-item-title>
           </v-list-item-content>

         </v-list-item>
      </v-list>

   </div>
</template>

<script>
import lodash from "lodash"

export default{
   data: function () {
      return {
         computerTypeOptions : ["Desktop", "Laptop", "Both"],
         computerBrandOptions : ["Acer", "Apple", "Dell", "HP", "Lenovo"],
         processorBrandOptions :  ["AMD", "Intel"],
         processorModelOptions :  ["i5", "i7", "i9", "Ryzen 5", "Ryzen 7"],
         graphicsBrandOptions :  ["NVIDIA"],
         ramSizeOptions : ["4", "8", "16", "32"],
         storageSizeOptions :  ["250GB", "500GB", "1TB"],
         screenSizeOptions :  ["13inch", "15inch"],
         selections: {
            computerTypeSelected : [],
            computerBrandSelected : [],
            processorBrandSelected : [],
            processorModelSelected : [],
            graphicsBrandSelected : [],
            ramSizeSelected : [],
            storageSizeSelected : [],
            screenSizeSelected : []
         }
      }
   },
   methods: {
      applyFilters(){
         var temp = lodash.cloneDeep(this.selections)
         this.$store.commit('SET_FILTERS', temp )
      }
   },
}
</script>

<style scoped>
  .group{
      margin: 0;
      padding: 0;
  }
  .selection{
      padding-left: 10px;
  }
  .small{
      margin: 0;
      padding-bottom: 0;
  }
  h4{
   padding-left: 17px;
  }
</style>

