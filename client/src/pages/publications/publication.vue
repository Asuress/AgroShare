<template>
  <v-card color="red">
    <v-text-field>{{this.id}}</v-text-field>
  </v-card>
</template>

<script>
import router from "@/router";

export default {
  // props: {
  //   id: null
  // },
  created() {
    console.log("router", this.$router);
    console.log("$router.params", this.$router.params);
    this.id = this.$router.params;
  }
}
</script>

<style scoped lang="sass">

</style>
