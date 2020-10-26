<template>
	<div >	
		<form action="https://compucompare.com/tempSearch" @submit.prevent="processForm">	
			<div class="input-container">
				<input type="text" name="query" placeholder="Search by Brand or Model" class="search-bar" v-model="query">
				<button type="submit">
					<svg xmlns="http://www.w3.org/2000/svg" width="20" height="25" fill="#000000" viewBox="0 0 256 256" class="glass">
						<rect width="256" height="256" fill="none"></rect>
						<circle cx="116" cy="116" r="84" fill="none" stroke="#000000" stroke-linecap="round" stroke-linejoin="round" stroke-width="16"></circle>
						<line x1="175.39356" y1="175.40039" x2="223.99414" y2="224.00098" fill="none" stroke="#000000" stroke-linecap="round" stroke-linejoin="round" stroke-width="16"></line>
					</svg>
				</button>
			</div>
		</form>
	</div>
</template>

<script>
export default{
	name: "Search",
	components: {
	},
	methods:{
		processForm(){
			this.$store.dispatch('generalSearch', this.query)
		}
	},
	data(){
		return{
			query: ''
		}
	}
};
</script>

<style scoped>
	.search-bar{
		/*margin: 1rem;
		margin-right: .3rem;
		border-radius: .3rem;
		border: .1rem solid black;
		height: 2rem;
		width: 25rem;*/
		border: none;
		padding-left: 1rem;
		padding-right: 1rem;
		grid-area: search; 
		padding-bottom: 0;
		padding-top: 0;
	}
	button{
		background-color: #c1e3f5;
		border: none;
		grid-area: icon;
		padding: 0;
	}
	button:hover{
		background-color: #bbdced;
	}
	.search-bar:focus {
		outline: none;
	}
	.input-container{
		border: .15rem solid black;
		border-radius: .3rem;
		width: 500px;
		height: 40px;
		display: grid;
		grid-template-columns: 90% 10%;
		grid-template-row: 100%; 
		grid-template-areas: "search icon";

	}
	form{
		display: flex;
		justify-content: center;
	}
</style>