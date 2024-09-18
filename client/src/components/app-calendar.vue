<template>
  <v-date-picker
    v-model="dates"
    scrollable
    :header="'Выберите дату аренды'"
    :allowed-dates="!reservedDates"
    locale="ru-RU"
    :min="new Date()"
    multiple
    first-day-of-week="1"
    range
  >
  </v-date-picker>
</template>

<script>
export default {
  data() {
    return {
      dates: null,
      modal: false,
      reservedDates: null
    };
  },
  methods: {
    saveDate() {
      this.modal = false;
      console.log("Selected date:", this.dates);
    }
  }
}
</script>
