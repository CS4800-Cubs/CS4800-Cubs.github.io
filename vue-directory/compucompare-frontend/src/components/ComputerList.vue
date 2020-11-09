<template>
	<div class="computer-list">
		<div v-for="computer in computers" :key="computer.id">
			<v-card
				class="card"
				outlined
			>
				<v-list-item three-line>
					<v-list-item-content>
						<div class="overline mb-4">
							{{computer.brand}} - {{computer.model}}
						</div>
						<!--
						<h3>
							{{computer.title}}
						</h3>-->
						<v-list-item-subtitle>Specifications</v-list-item-subtitle>
					</v-list-item-content>
	
					<v-list-item-avatar
						tile
						size="80"
						color="grey"
					></v-list-item-avatar>
					<v-btn
						text
						v-on:click="emitEvent(computer)"
					>
						<v-icon>mdi-chevron-right</v-icon>
					</v-btn>
				</v-list-item>
	
				<v-card-actions>
					<v-btn
					outlined
					rounded
					text
					v-on:click="addToCompare(computer)"
					small
					>
						Compare
					</v-btn>
				</v-card-actions>
			</v-card>
		</div>
	</div>
</template>

<script>
export default{
	props: ['callback'],
	computed: {
		computers(){
			//Apply Filters
			var filters = this.$store.state.filters
			return this.$store.state.computers.filter(function(obj){
				var matchingComputerBrand = true
				if(!filters["computerBrandSelected"].includes("All")){
					matchingComputerBrand = filters["computerBrandSelected"].includes(obj.brand)
				}
				return matchingComputerBrand
			})
		}
	},
	methods: {
		addToCompare(computer){
			this.$store.dispatch('addToCompare', computer)
		},
		emitEvent: function (computer) {
            this.$emit('expandDevice', computer);
            console.log(computer)
      }
	},
}
</script>

<style scoped>
	.computer-list{
		display: flex;
		flex-direction: column;
		padding: 10px;
		padding-top: 10;
		justify-content: right; 
	}
	.card{
		margin-right: 5px;
		margin-left: 5px;
		margin-bottom: 7.5px;
	}
</style>