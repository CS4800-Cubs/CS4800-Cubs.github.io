<template>

   <div style="padding:1em; display:flex; flex-direction:row; flex:auto;">
      <v-img class="mx-auto" contain width="300px" height="300px" :src="computer.result.thumbnailUrl"> </v-img>
      <!-- <h1 style= "font-size:150%; text-align:left; color: #6495ED;">{{computer.result.brand}} - {{computer.result.displayName}}</h1> -->
      <div style="display: flex; flex-direction:column; flex:auto; justify-content: space-between; align-content: left;">
          <v-list-item-title style= "font-size: 1em;" class="headline mb-1 blue--text">{{computer.result.brand}} - {{computer.result.displayName}}</v-list-item-title>
          <div class="specs">
              <h2 style= "color: #6495ED; font-size:2em;"> Specs: </h2>
              <p> Model: {{computer.result.model}}</p>
              <p> Graphics: {{computer.result.graphics.brand}} {{computer.result.graphics.model}}</p>
              <p> Processor: {{computer.result.processor.brand}} {{computer.result.processor.model}}</p>
          </div>
          <h3 style= "text-align:center; font-size:130%">
          <a :href= "computer.result.pageUrl" style="text-decoration:none;"><v-btn tile> View at Bestbuy!</v-btn> </a>
          </h3>
      </div>
   </div>

</template>

<script>
export default{
   name: "AdvanceDetails",
   props: ["computer"],
}
</script>

<style scoped>
.v-list-item__title, .v-list-item__subtitle {
  overflow: unset;
  text-overflow: unset;
  white-space: unset;
}
.v-list-item__title {
  flex: unset;
}
div.specs > p {
  display: list-item;          /* This has to be "list-item"                                               */
  list-style-type: disc;       /* See https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-type     */
  list-style-position: inside; /* See https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-position */
}
div.v-image__image {
  max-width:100%;
  max-height:100%;
  height: auto;
}
</style>
