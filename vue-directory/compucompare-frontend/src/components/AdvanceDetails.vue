<template>
   <div>
      <h1>{{computer.result.brand}}</h1>
      <h1>{{computer.result.model}}</h1>
   </div>
   
</template>

<script>
export default{
   name: "AdvanceDeatil",
   props: ["computer"],
}
</script>