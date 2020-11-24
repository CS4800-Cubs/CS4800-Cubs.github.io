<template>

   <div style="padding:1em; display:flex; flex-direction:row; flex:auto;">
      <div style="padding:0 2em 0 0">
        <v-img class="mx-auto" contain width="500px" height="500px" :src="computer.thumbnailUrl"> </v-img>
      </div>
      <div style="display: flex; flex-direction:column; flex:auto; justify-content: space-around; align-content: left;">
          <div>
              <v-list-item-title style= "font-weight: 600 " class="headline mb-1 blue--text">{{computer.brand}} - {{computer.displayName}}</v-list-item-title>
              <div class="specs" style="margin: 2em 0 0 0;">
                  <h2 style= "color: #2196F3; font-size:1.5em; font-weight: 400"> Specifications: </h2>
                  <p> Model: {{computer.model}}</p>
                  <p> Graphics Card: {{computer.graphics.brand}} {{computer.graphics.model}}</p>
                  <p> Central Processing Unit: {{computer.processor.brand}} {{computer.processor.model}}</p>
              </div>
          </div>
          <h3 style= "text-align:center; font-size:130%">
          <a :href= "computer.pageUrl" style="text-decoration:none;"><v-btn color="#2196F3" tile> View at Bestbuy!</v-btn> </a>
          </h3>
      </div>
   </div>

</template>

<script>
export default{
   name: "SurveyAdvanceDetails",
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
    display: list-item;
    list-style-type: disc;
    list-style-position: inside;
    font-size: 1.25em;
}
div.v-image__image {
  max-width:100%;
  max-height:100%;
  height: auto;
}
</style>


