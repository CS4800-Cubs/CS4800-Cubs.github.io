<template>
  <div>
    <v-app-bar
      app
      color="white"
      flat
    >
      <v-container class="py-0 fill-height">
        <h3>
          CompuCompare
        </h3>
        <v-spacer></v-spacer>

        <!-- Navigation Links -->
        <v-btn
          text
          small
        >
          <router-link to="/" style="text-decoration:none; color: black;">Dashboard</router-link>
        </v-btn>
        <v-btn
          text
          small
        >
          <router-link to="/DeviceRecommendations" style="text-decoration:none; color: black;">Device Recommendations</router-link>
        </v-btn>


          

        <v-spacer></v-spacer>
        <!-- Search Bar -->
        <v-form action="/" method="get">  
          <v-responsive max-width="350">
            <v-toolbar
              height = "50px"
              >
              <v-text-field
                v-model="query"
                dense
                flat
                hide-details
                rounded
                background-color="#E3F2FD"
                placeholder="Search"
                solo
                @keypress.enter.prevent
              >
              </v-text-field>

              <v-btn
                v-on:click="processForm()"
                icon
                class="hidden-xs-only"
              >
                <v-icon
                  dense
                > mdi-magnify
                </v-icon>
              </v-btn> 
            </v-toolbar>
          </v-responsive>
        </v-form>
        <!--
        <v-spacer></v-spacer>
         User 
        <v-avatar
          class="mr-10"
          color="blue darken-1"
          size="32"
        ></v-avatar> -->
      </v-container>
    </v-app-bar>  </div>
</template>

<script>
  export default {
    data: () => ({
      links: [
        'Dashboard',
        'Device Recommendations',
      ], 
      query: '',
    }),
      methods:{
    processForm(){
      this.$store.dispatch('generalSearch', this.query)
    }
  }
}
</script>

<style scoped>

</style>