<template>
    <div>
        <div id="surveyElement">
            <Survey/>
        </div>
    </div>
</template>

<script>
import Survey from "../components/Survey";
export default{
	name: "DeviceRecommendations",
  components: {
    Survey
  }
};
</script>

<style scoped>
#surveyElement {
    text-align: left;
    margin: 150px;
}

</style>

