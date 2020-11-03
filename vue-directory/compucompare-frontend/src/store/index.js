import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
	state: {
		computers: [],
		selected: []
	},
	mutations: {
		SET_COMPUTERS(state, data){
			state.computers = data
		},
		addToCart(state, targetComputer){
			state.selected.push(targetComputer)
		}
	},
	actions: {
		async generalSearch({commit}, query){
			console.log(query)
			axios
				.get(`https://jsonplaceholder.typicode.com/posts?_limit=5`)
				.then( res =>
					commit('SET_COMPUTERS', res.data)
				)
				.catch(error => console.log(error))
		},
		async filteredSearch({commit}, params){
			console.log(params)
			axios
				.get(`https://compucompare/generalSearch`)
				.then( res =>
					commit('SET_COMPUTERS', res.data)
				)
				.catch(error => console.log(error))
		},
		addToCompare({commit}, targetComputer){
			commit('addToCart', targetComputer)
		}
	},
	getters: {

	},
		modules: {
	}
})
