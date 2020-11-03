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
			for (var i = state.selected.length - 1; i >= 0; i--) {
				if(state.selected[i].id == targetComputer.id){
					return
				}
			}
			state.selected.push(targetComputer)
			sessionStorage.setItem("selected", JSON.stringify(state.selected))
		},
		loadCart(state){
			state.selected = JSON.parse(sessionStorage.getItem("selected"))
		},
		removeFromCart(state, targetComputer){
			state.selected.splice(state.selected.indexOf(targetComputer), 1)
			sessionStorage.setItem("selected", JSON.stringify(state.selected))
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
		},
		loadCart({commit}){
			commit('loadCart')
		},
		removeFromCart({commit}, targetComputer){
			commit('removeFromCart', targetComputer)
		}
	},
	getters: {

	},
		modules: {
	}
})
