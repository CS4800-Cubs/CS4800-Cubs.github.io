<template>
    <div>
        <h3> Are you looking for a laptop or a desktop? </h3>
        <input type="radio" value="true" v-model="searchFor.portable" > Laptop
        <br>
        <input type="radio" value="false" v-model="searchFor.portable" > Desktop
        <br><br>

        <h3> Purpose for laptop or desktop? Select all that apply </h3>
        <input type="checkbox" value="gaming" v-model="searchFor.categories" > Gaming or Streaming
        <br>
        <input type="checkbox" value="editing" v-model="searchFor.categories" > Editing
        <br>
        <input type="checkbox" value="work" v-model="searchFor.categories" > Work
        <br>
        <input type="checkbox" value="education" v-model="searchFor.categories" > Education
        <br><br>

        <h3> Which brand(s) do you prefer? </h3>
        <input type="checkbox" value="acer" v-model="searchFor.brands"> Acer
        <br>
        <input type="checkbox" value="apple" v-model="searchFor.brands"> Apple
        <br>
        <input type="checkbox" value="asus" v-model="searchFor.brands"> Asus
        <br>
        <input type="checkbox" value="dell" v-model="searchFor.brands"> Dell
        <br>
        <input type="checkbox" value="hp" v-model="searchFor.brands"> Hp
        <br>
        <input type="checkbox" value="lenovo" v-model="searchFor.brands"> Lenovo
        <br>
        <input type="checkbox" value="lg" v-model="searchFor.brands"> LG
        <br>
        <input type="checkbox" value="microsoft" v-model="searchFor.brands"> Microsoft
        <br>
        <input type="checkbox" value="msi" v-model="searchFor.brands"> MSI
        <br>
        <input type="checkbox" value="razer" v-model="searchFor.brands"> Razer
        <br>
        <input type="checkbox" value="samsung" v-model="searchFor.brands"> Samsung
        <br><br>
        <v-row
            align="center"
            justify="space-around"
        >
            <v-btn v-on:click="submitSurvey">Submit</v-btn>
        </v-row>

    </div>
</template>

<script>
export default{
        name: 'Survey',
        data()
        {
            return{
                searchFor: {
                    portable:null,
                    categories:[],
                    brands:[]
                }
            }
        },
        methods:{
            submitSurvey()
            {
                console.log("Pre Dispatch")
                this.$store.dispatch('surveySearch', this.searchFor)
            }


        }
}
</script>