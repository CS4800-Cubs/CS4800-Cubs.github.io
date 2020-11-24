<template>
   <div>
       <h1 style= "font-size:150%; text-align:center; color: #6495ED;">{{computer.brand}} - {{computer.displayName}}</h1>
      <v-img class="mx-auto" contain height="200px" width="200px" :src="computer.thumbnailUrl"> </v-img>
      <h2 style= "color: #6495ED; font-size:120%;"> Specs: </h2>
      <p> Model: {{computer.model}}</p>
      <p> Graphics: {{computer.graphics.brand}} {{computer.graphics.model}}</p>
      <p> Processor: {{computer.processor.brand}} {{computer.processor.model}}</p>
      <h3 style= "text-align:center; font-size:130%">
      <a :href= "computer.pageUrl"><button style= "padding: 10px; color: white; border-radius: 50%; background-color: #6495ED;"> View at Bestbuy!</button> </a>
      </h3>
   </div>
</template>

<script>
export default{
   name: "SurveyAdvanceDetails",
   props: ["computer"],
}
</script>

