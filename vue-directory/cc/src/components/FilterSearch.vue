<template>
	<div>
		<form @submit.prevent="processFilter">
			<a class="collapsible" @click="collapse" id="open-collapsible" href="#">Brand</a>
			<div class="brand-container" >
				<div>
					<input type="checkbox" id="hp" value="hp" v-model="checkedBrands">
					<label for="hp">HP</label>
				</div>
				<div>
					<input type="checkbox" id="intel" value="intel" v-model="checkedBrands">
					<label for="intel">Intel</label>
				</div>
				<div>
					<input type="checkbox" id="apple" value="apple" v-model="checkedBrands">
					<label for="apple">Apple</label>
				</div>
				<div>
					<input type="checkbox" id="dell" value="dell" v-model="checkedBrands">
					<label for="dell">Dell</label>
				</div>
			</div><br>
			<a class="collapsible" @click="collapse" id="open-collapsible" href="#">Memory Capacity</a>
			<div class="memory-range">
				<div>
					<input type="checkbox" id="memory-capacity-4" value="4" v-model="checkedBrands">
					<label for="memory-capacity-4memory-capacity-4">4</label>
				</div>
				<div>
					<input type="checkbox" id="memory-capacity-8" value="8" v-model="checkedBrands">
					<label for="memory-capacity-8">8</label>
				</div>
				<div>
					<input type="checkbox" id="memory-capacity-16" value="16" v-model="checkedBrands">
					<label for="memory-capacity-16">16</label>
				</div>
				<div>
					<input type="checkbox" id="memory-capacity-32" value="32" v-model="checkedBrands">
					<label for="memory-capacity-32">32</label>
				</div>
			</div>
			<br>
			<button type="submit" class="apply">Apply</button>
		</form>
	</div>
</template>

<script>

export default{
	name: "FilterSearch",
	methods:{
		processFilter(){
			this.$store.dispatch('filteredSearch', this.checkedBrands)
		},
		collapse(){
			var coll = document.getElementById("open-collapsible");
			var content = coll.nextElementSibling
			if (content.style.display === "block") {
				content.style.display = "none";
			} else {
				content.style.display = "block";
				}
		}
	},
	data(){
		return{
			checkedBrands: []
		}
	}
}

</script>

<style scoped>
	.collapsible{
		cursor: pointer;
	}
	.brand-container{
		display: flex;
		flex-direction: column;
	}
	.apply{
		width: 50px;
	}
	.memory-upper-bound, .memory-lower-bound{
		width: 50px;
	}
</style>