<template>
    <survey :survey="survey" />
</template>

<script>
    import * as Survey from "survey-vue";

    import "survey-vue/survey.css";

    Survey.StylesManager.applyTheme("default");

    export default {
        name: "surveyjs-component",
        data() {





            const json = {
                          pages: [
                           {
                            name: "page1",
                            elements: [
                             {
                              type: "radiogroup",
                              name: "portable",
                              title: "Are you looking for a laptop or a desktop?",
                              isRequired: true,
                              choices: [
                               {
                                value: "true",
                                text: "Laptop"
                               },
                               {
                                value: "false",
                                text: "Desktop"
                               },
                               {
                                value: "",
                                text: "Both"
                               }
                              ]
                             },
                             {
                              type: "checkbox",
                              name: "categories",
                              title: "Purpose for laptop or desktop? Select all that apply",
                              isRequired: true,
                              choices: [
                               {
                                value: "Editing",
                                text: "Editing"
                               },
                               {
                                value: "Gaming",
                                text: "Gaming or Streaming"
                               },
                               {
                                value: "Work",
                                text: "Work"
                               },
                               {
                                value: "Education",
                                text: "Education"
                               }
                              ],
                              choicesOrder: "asc",
                              hasOther: true,
                              otherText: "Other",
                              hasSelectAll: true,
                              noneText: "Other"
                             },
                             {
                              type: "checkbox",
                              name: "brands",
                              title: "Which brand(s) do you prefer? Select up to 3 options ",
                              isRequired: true,
                              validators: [
                               {
                                type: "answercount",
                                text: "Please select up to 3 options",
                                maxCount: 3
                               },
                               {
                                type: "expression"
                               }
                              ],
                              choices: [
                               {
                                value: "Acer",
                                text: "Acer"
                               },
                               {
                                value: "Apple",
                                text: "Apple"
                               },
                               {
                                value: "Asus",
                                text: "Asus"
                               },
                               {
                                value: "Dell",
                                text: "Dell"
                               },
                               {
                                value: "hp",
                                text: "hp"
                               },
                               {
                                value: "Kano",
                                text: "Kano"
                               },
                               {
                                value: "Lenovo",
                                text: "Lenovo"
                               },
                               {
                                value: "LG",
                                text: "LG"
                               },
                               {
                                value: "Microsoft",
                                text: "Microsoft"
                               },
                               {
                                value: "MSI",
                                text: "MSI"
                               },
                               {
                                value: "Razer",
                                text: "Razer"
                               },
                               {
                                value: "Samsung",
                                text: "Samsung"
                               }
                              ],
                              otherText: "None",
                              hasNone: true,
                              noneText: "No preference"
                             }
                            ]
                           }
                          ],
                          "navigateToUrl" : "/"

                         };
const survey = new Survey.Model(json);
        survey.onComplete.add(function () {
                //this.$store.state.windowPosition = 3
                window.location.href = "/surveySearch"; //?results="  + JSON.stringify(result.data, null, 3);


            });
            return {
                survey: survey

            };
        }
    };
</script>

<style scoped>
</style>
