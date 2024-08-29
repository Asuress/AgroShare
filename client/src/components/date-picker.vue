<template>
    <v-date-input
      v-model="model"
      label="Select range"
      max-width="368"
      multiple="range"
    ></v-date-input>
</template>
<script>
export default {
  name: "date-picker",
  data: () => ({
    model: null,
  }),
}
</script>
