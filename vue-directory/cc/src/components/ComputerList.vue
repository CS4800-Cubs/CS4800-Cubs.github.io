<template>
	<div class="computer-list">
		<div v-for="computer in computers" :key="computer.id">
			<p>{{computer.title}}{{computer.brand}} {{computer.model}}</p>
		</div>
	</div>
</template>

<script>
export default{
	computed: {
		computers(){
			return this.$store.state.computers
		}
	},
}
</script>

<style scoped>
	
</style>