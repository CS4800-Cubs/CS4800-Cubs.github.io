<template>
	<div>
		<h1>Compu Compare</h1>
		<search-bar/>
		<filter-menu/>
		<computer-list/>
	</div>
</template>

<script>
import SearchBar from '../components/SearchBar'
import FilterSearch from '../components/FilterSearch'
import ComputerList from '../components/ComputerList'

export default{
	name: 'Computer',
	components: {
		'search-bar': SearchBar,
		'filter-menu': FilterSearch,
		'computer-list': ComputerList
	}
}
</script>

<style scoped>
	
</style>