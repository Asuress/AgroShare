<template>
  <publications @usernameChanged="handleUsernameChange"/>
</template>

<script>
import axios from "axios";
import DatePicker from "@/components/app-calendar.vue";
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
      searchField: null,
      items: [
        {
          id: 1,
          title: 'Applications :',
          children: [
            { id: 2, title: 'Calendar : app' },
            { id: 3, title: 'Chrome : app' },
            { id: 4, title: 'Webstorm : app' },
          ],
        },
        {
          id: 5,
          title: "Category1",
          children: [
            { id: 2, title: 'Calendar : app' },
            { id: 3, title: 'Chrome : app' },
            { id: 4, title: 'Webstorm : app' },
          ],
        },
        {
          id: 2,
          title: "Category2"
        }
      ]
    }
  },
  methods: {
    handleUsernameChange(newUsername) {
      this.$emit('usernameChanged', newUsername);
    }
  },
  mounted() {
  }
}
</script>
