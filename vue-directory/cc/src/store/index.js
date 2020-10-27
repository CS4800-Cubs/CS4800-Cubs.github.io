import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
	state: {
		computers: []
	},
	mutations: {
		SET_COMPUTERS(state, data){
			state.computers = data
		}
	},
	actions: {
		async generalSearch({commit}, query){
			axios
				.get(`https://compucompare/tempSearch`)
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
		}
	},
	getters: {

	},
		modules: {
	}
})
