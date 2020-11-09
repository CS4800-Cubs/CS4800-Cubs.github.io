<template>
    <div>
    <v-card class="mx=auto" max-width="450">
      <!-- Computer Type -->
      <v-card-text style="padding-bottom: 0;">
        <p class="font-weight-bold">Computer Type</p>
        <v-chip-group
          v-model="selections.computerTypeSelected"
          column
          single
          mandatory>
          <v-chip filter 
            outlined 
            v-for="option in computerTypeOptions" 
            :key="option" 
            small 
            flat>
            {{option}}
          </v-chip>
        </v-chip-group>
      </v-card-text>
      <!-- Computer Brand -->
      <v-card-text>
        <p class="font-weight-bold">Computer Brand</p>
        <v-chip-group
          v-model="selections.computerBrandSelected"
          column
          multiple
          mandatory>
          <v-chip 
            filter
            outlined 
            v-for="option in computerBrandOptions" 
            :key="option" 
            small 
            flat
            :value="option">
            {{option}}
          </v-chip>
        </v-chip-group>
      </v-card-text>
      <!-- Processor Brand -->
      <v-card-text>
        <p class="font-weight-bold">Processor Brand</p>
        <v-chip-group
          v-model="selections.processorBrandSelected"
          column
          multiple
          mandatory>
          <v-chip 
            filter
            outlined 
            v-for="option in processorBrandOptions" 
            :key="option" 
            small 
            flat
            :value="option">
            {{option}}
          </v-chip>
        </v-chip-group>
      </v-card-text>
      <!-- Ram Size Options -->
      <v-card-text>
        <p class="font-weight-bold">Ram Size</p>
        <v-chip-group
          v-model="selections.ramSizeSelected"
          row
          single
          mandatory>
          <v-chip 
            filter
            outlined 
            v-for="option in ramSizeOptions" 
            :key="option" 
            small 
            flat
            :value="option">
            {{option}}
          </v-chip>
        </v-chip-group>
      </v-card-text>
      <!-- Storage Size Options -->
      <v-card-text>
        <p class="font-weight-bold">Storage Size</p>
        <v-chip-group
          v-model="selections.storageSizeSelected"
          row
          single
          mandatory>
          <v-chip 
            filter
            outlined 
            v-for="option in storageSizeOptions" 
            :key="option" 
            small 
            flat
            :value="option">
            {{option}}
          </v-chip>
        </v-chip-group>
      </v-card-text>

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
                 small
                 v-on:click="applyFilters">
                 Apply Filter
               </v-btn> 
             </v-list-item-title>
           </v-list-item-content>

         </v-list-item>
      </v-list>
    </v-card>

   </div>
</template>

<script>
import lodash from "lodash"

export default{
   data: function () {
      return {
         computerTypeOptions : ["Both", "Desktop", "Laptop"],
         computerBrandOptions : ["All", "Acer", "Apple", "Dell", "HP", "Lenovo"],
         processorBrandOptions :  ["All", "AMD", "Intel"],
         //processorModelOptions :  ["i5", "i7", "i9", "Ryzen 5", "Ryzen 7"],
         //graphicsBrandOptions :  ["NVIDIA"],
         ramSizeOptions : ["All", "4", "8", "16", "32"],
         storageSizeOptions :  ["All", "250GB", "500GB", "1TB"],
         //screenSizeOptions :  ["13inch", "15inch"],
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
</style>

