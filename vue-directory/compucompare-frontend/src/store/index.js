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
         compareResults: []
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
            console.log("Alec")
            state.results = results
            sessionStorage.setItem("results", JSON.stringify(state.results))
            window.location.href = "/"
		},
		loadSurveyResults(state){
            if(JSON.parse(sessionStorage.getItem("results")) != null){
                state.results = JSON.parse(sessionStorage.getItem("results"))
            }
      },
      SET_COMPARE_RESULTS(state, data){
         state.compareResults = data
      }
	},

	actions: {
      async compareComputers({commit}){
         var computerIds = []
         for(var i=0; i<this.state.selected.length; i++){
            computerIds.push(this.state.selected[i].id) 
         }
         axios
            .post(`https://compucompare.com/compare`, 
               {
                     computerIds: computerIds,
                     surveyResponse: {"portable": "true", "categories": ["gaming"], "brands": ["hp", "apple", "razer"]} 
               }
            )
            .then( res => commit('SET_COMPARE_RESULTS', res.data))
            .catch( error => console.log(error))
      },
		async generalSearch({commit}, query){

			sessionStorage.setItem('query', query)

			axios
				.get(`https://compucompare.com/generalSearch`, {
          params: {
            query: query
          }
        })
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
		loadSurveyResults({commit}){
            commit('loadSurveyResults')
		},

		async surveySearch({commit}, surveyResults){
            console.log("Pre Post")
            console.log(surveyResults)
            axios
               .post('https://compucompare.com/surveySearch', surveyResults, {timeout: 10000})
               .then( res => commit('SET_SurveyResults', res.data))
               .catch(error => console.log(error)) 
		}
	},

	getters: {

	},
		modules: {
	}
})
