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
                              name: "question1",
                              title: "Are you looking for a laptop or a desktop?",
                              isRequired: true,
                              choices: [
                               {
                                value: "item1",
                                text: "Laptop"
                               },
                               {
                                value: "item2",
                                text: "Desktop"
                               },
                               {
                                value: "item3",
                                text: "Both"
                               }
                              ]
                             },
                             {
                              type: "checkbox",
                              name: "question2",
                              title: "Purpose for laptop or desktop? Select all that apply",
                              isRequired: true,
                              choices: [
                               {
                                value: "item1",
                                text: "Editing"
                               },
                               {
                                value: "item2",
                                text: "Gaming or Streaming"
                               },
                               {
                                value: "item3",
                                text: "Work"
                               },
                               {
                                value: "item4",
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
                              name: "question3",
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
                                value: "item1",
                                text: "Acer"
                               },
                               {
                                value: "item3",
                                text: "Apple"
                               },
                               {
                                value: "item2",
                                text: "Asus"
                               },
                               {
                                value: "item12",
                                text: "Dell"
                               },
                               {
                                value: "item4",
                                text: "hp"
                               },
                               {
                                value: "item5",
                                text: "Kano"
                               },
                               {
                                value: "item6",
                                text: "Lenovo"
                               },
                               {
                                value: "item7",
                                text: "LG"
                               },
                               {
                                value: "item8",
                                text: "Microsoft"
                               },
                               {
                                value: "item9",
                                text: "MSI"
                               },
                               {
                                value: "item10",
                                text: "Razer"
                               },
                               {
                                value: "item11",
                                text: "Samsung"
                               }
                              ],
                              otherText: "None",
                              hasNone: true,
                              noneText: "No preference"
                             }
                            ]
                           }
                          ]
                         };
const survey = new Survey.Model(json);
            return {
                survey: survey
            };
        }
    };
</script>

<style scoped>
</style>
