import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
	state: {
		computers: [],
		selected: [],
		filters: {
            computerTypeSelected : ["Both"],
            computerBrandSelected : ["All"],
            processorBrandSelected : ["All"],
            //processorModelSelected : [],
            //graphicsBrandSelected : [],
            ramSizeSelected : ["All"],
            storageSizeSelected : ["All"],
            screenSizeSelected : []
         },
         results: [],
         windowPosition: 0,
         query: [],
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
			if(JSON.parse(sessionStorage.getItem("selected")) != null){
				state.selected = JSON.parse(sessionStorage.getItem("selected"))
			}
		},
		removeFromCart(state, targetComputer){
			state.selected.splice(state.selected.indexOf(targetComputer), 1)
			sessionStorage.setItem("selected", JSON.stringify(state.selected))
		},
		SET_FILTERS(state, filtersObject){
			state.filters = filtersObject
		},
		SET_SurveyResults(state, results){
            state.results = results
		}


	},
	actions: {
		async generalSearch({commit}, query){

			sessionStorage.setItem('query', query)

			axios
				.get(`https://jsonplaceholder.typicode.com/posts?_limit=5`)
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
		},
		setFilters({commit}, filtersObject){
			commit('SET_FILTERS', filtersObject)
		},
		async surveySearch({commit}, surveyResults){
            axios
                   .post('https://compucompare/surveySearch', surveyResults)
                   .then( res => commit('SET_SurveyResults', res.data))
				.catch(error => console.log(error))
				}
	},

	getters: {

	},
		modules: {
	}
})
