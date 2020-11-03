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
							{{computer.brand}}{{computer.id}}
						</div>
						<h3>
							{{computer.title}}
						</h3>
						<v-list-item-subtitle>{{computer.model}}</v-list-item-subtitle>
					</v-list-item-content>
	
					<v-list-item-avatar
						tile
						size="80"
						color="grey"
					></v-list-item-avatar>
				</v-list-item>
	
				<v-card-actions>
					<v-btn

					outlined
					rounded
					text
					v-on:click="addToCompare(computer)"
					>
						Button
					</v-btn>
				</v-card-actions>
			</v-card>
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
	methods: {
		addToCompare(computer){
			this.$store.dispatch('addToCompare', computer)
		}
	}
}
</script>

<style scoped>
	.computer-list{
		display: flex;
		flex-direction: column;
		padding: 10px;
		justify-content: right; 
	}
	.card{
		margin-right: 5px;
		margin-left: 5px;
		margin-top: 7.5px;
		margin-bottom: 7.5px;
	}
</style>