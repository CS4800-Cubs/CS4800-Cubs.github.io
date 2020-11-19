<template>

   <div>
       <h1 style= "font-size:150%; text-align:center; color: #6495ED;">{{computer.result.brand}} - {{computer.result.displayName}}</h1>
      <v-img class="mx-auto" contain height="200px" width="200px" :src="computer.result.thumbnailUrl"> </v-img>
      <h2 style= "color: #6495ED; font-size:120%;"> Specs: </h2>
      <p> Model: {{computer.result.model}}</p>
      <p> Graphics: {{computer.result.graphics.brand}} {{computer.result.graphics.model}}</p>
      <p> Processor: {{computer.result.processor.brand}} {{computer.result.processor.model}}</p>
      <h3 style= "text-align:center; font-size:130%">
      <a :href= "computer.result.pageUrl"><button style= "padding: 10px; color: white; border-radius: 50%; background-color: #6495ED;"> View at Bestbuy!</button> </a>
      </h3>
   </div>

</template>

<script>
export default{
   name: "AdvanceDetails",
   props: ["computer"],
}
</script>

