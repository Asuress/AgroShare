<template>
  <publications/>
</template>

<script>
import axios from "axios";
import DatePicker from "@/components/date-picker.vue";
import UserHelper from "@/utils/user-helper";
import Publications from "@/pages/publications/publications.vue";


export default {
  name: "index",
  components: {
    Publications,
    DatePicker,
    UserHelper
  },
  data() {
    return {
      publications: null,
      searchField: null
    }
  },
  methods: {

  },
  mounted() {
    console.log(localStorage.getItem('access_token'));
    axios.get("/publications/list/last").then(response => {
      console.log("response:", response);
      this.publications = response.data;
    }).catch(error => {
      console.log("error", error)
    });
  }
}
</script>
