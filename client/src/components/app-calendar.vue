<template>
  <v-container flex>
    <v-row>
      <v-col>
        <v-switch label="Использовать промежуток"
                  hide-details
                  color="blue"
                  v-model="useRange"
        >
        </v-switch>
      </v-col>
      <v-col>
        <v-btn class="my-custom-background-button-primary"
               @click="resetDates">Сбросить даты</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-date-picker
        v-model="dates"
        header="Выберите дату аренды"
        :allowed-dates="!reservedDates"
        locale="ru-RU"
        :min="new Date()"
        :multiple="useRange ? 'range' : ''"
        first-day-of-week="1"
      >
      </v-date-picker>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      dates: null,
      modal: false,
      useRange: true,
      reservedDates: null
    };
  },
  methods: {
    saveDate() {
      this.modal = false;
      console.log("Selected date:", this.dates);
    },
    cancel() {
      console.log("cancel");
    },
    resetDates() {
      console.log("reset dates");
      this.dates = [];
    }
  }
}
</script>
