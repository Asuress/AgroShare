<template>
  <v-container flex>
    <v-col align-self="end">
      <v-row>
        <v-col align-self="end">
          <v-switch label="Использовать промежуток"
                    hide-details
                    color="blue"
                    v-model="useRange"
                    class="switch-end"
          >
          </v-switch>
        </v-col>
        <v-col>
          <v-btn class="my-custom-background-button-primary"
                 @click="resetDates">Сбросить даты
          </v-btn>
        </v-col>
      </v-row>
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
    </v-col>
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
    },
    cancel() {
    },
    resetDates() {
      this.dates = [];
    }
  }
}
</script>

<style>
.switch-end {
  display: flex;
  justify-content: end;
}
</style>
