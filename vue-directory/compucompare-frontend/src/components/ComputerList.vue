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
							{{computer.result.brand}} - {{computer.result.model}}
						</div>
						<!--
						<h3>
							{{computer.title}}
						</h3>-->
						<v-list-item-subtitle>{{computer.result.displayName}}</v-list-item-subtitle>
					</v-list-item-content>
	
					<v-list-item-avatar
						tile
						size="80"
					><v-img contain :src="computer.result.thumbnailUrl"></v-img></v-list-item-avatar>

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
					v-on:click="addToCompare(computer.result)"
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
				var matchingRamSize = true
				var matchingStorageSize = true
				var matchingProcessorBrand = true
				if(!filters["computerBrandSelected"].includes("All")){
					matchingComputerBrand = filters["computerBrandSelected"].includes(obj.result.brand)
				}
				if(!filters["ramSizeSelected"].includes("All")){
					matchingRamSize = filters["ramSizeSelected"].includes(obj.result.ram.memory)
				}
				if(!filters["storageSizeSelected"].includes("All")){
					if(obj.storage[0] != null){
						matchingStorageSize = filters["storageSizeSelected"].includes(obj.result.storage[0].capacity)
					}
					matchingStorageSize = false
				}
				if(!filters["processorBrandSelected"].includes("All")){
					matchingProcessorBrand = filters["processorBrandSelected"].includes(obj.result.processor.brand)
				}
				return matchingComputerBrand && matchingRamSize && matchingStorageSize && matchingProcessorBrand && matchingComputerType
			})
		}
	},
	methods: {
		addToCompare(computer){
			this.$store.dispatch('addToCompare', computer)
		},
		emitEvent: function (computer) {
            this.$emit('expandDevice', computer);
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