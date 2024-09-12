<template>
  <v-container>
    <!-- Основная карточка объявления -->
    <v-card class="ad-card" outlined>
      <v-row>
        <!-- Изображение товара -->
        <v-col cols="5">
          <v-img :src="ad.image" alt="Image of ad" height="400" contain></v-img>
          <v-btn icon color="red" @click="toggleFavorite">
            <v-icon>{{ isFavorite ? 'mdi-heart' : 'mdi-heart-outline' }}</v-icon>
          </v-btn>
        </v-col>

        <!-- Описание товара -->
        <v-col cols="7">
          <v-card-title class="headline">{{ ad.title }}</v-card-title>
          <v-card-subtitle class="category">
            Категория: {{ ad.category }}
          </v-card-subtitle>
          <p class="location">
            <v-icon left>mdi-map-marker</v-icon> {{ ad.location }}
          </p>
          <v-divider></v-divider>

          <v-card-text>
            <p class="price">{{ formatPrice(ad.price) }} руб.</p>
            <p>{{ ad.description }}</p>
            <v-divider></v-divider>
            <p class="meta-info">Дата публикации: {{ ad.date }}</p>
            <p class="meta-info">Просмотры: {{ ad.views }}</p>
          </v-card-text>

          <!-- Кнопки действий -->
          <v-card-actions>
            <v-btn color="primary" @click="viewDetails">Подробнее</v-btn>
            <v-btn color="secondary" @click="showContact">Показать телефон</v-btn>
          </v-card-actions>

          <!-- Информация о продавце -->
          <v-divider></v-divider>
          <v-card-subtitle class="seller-info">
            Продавец: {{ ad.seller.name }}
          </v-card-subtitle>
          <v-btn color="secondary" @click="viewSellerAds">Все объявления продавца</v-btn>
        </v-col>
      </v-row>

      <!-- Модальное окно для показа телефона -->
      <v-dialog v-model="contactDialog" persistent max-width="400">
        <v-card>
          <v-card-title>Контактная информация</v-card-title>
          <v-card-text>
            <p><strong>Телефон:</strong> {{ ad.contact.phone }}</p>
          </v-card-text>
          <v-card-actions>
            <v-btn color="primary" @click="contactDialog = false">Закрыть</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-card>

    <!-- Блок с похожими объявлениями -->
    <v-card class="similar-ads" flat>
      <v-card-title>Похожие объявления</v-card-title>
      <v-row>
        <v-col v-for="(similarAd, index) in similarAds" :key="index" cols="4">
          <v-card class="similar-ad-card" outlined>
            <v-img :src="similarAd.image" alt="Image of similar ad" height="150" contain></v-img>
            <v-card-title class="headline">{{ similarAd.title }}</v-card-title>
            <v-card-subtitle class="price">{{ formatPrice(similarAd.price) }} руб.</v-card-subtitle>
            <v-btn color="primary" small @click="viewSimilarAd(similarAd.id)">
              Смотреть
            </v-btn>
          </v-card>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
export default {
  props: {
    ad: {
      type: Object,
      required: true,
      default: () => ({
        title: "Пример объявления",
        category: "Электроника",
        location: "Москва",
        image: "https://via.placeholder.com/300",
        description: "Пример описания товара.",
        price: 10000,
        contact: { phone: "8 (999) 123-45-67" },
        seller: { name: "Иван Иванов" },
        date: "01.09.2024",
        views: 123,
        id: 1,
      }),
    },
  },
  data() {
    return {
      isFavorite: false,
      contactDialog: false,
      similarAds: [
        {
          id: 2,
          title: "Похожее объявление 1",
          price: 9500,
          image: "https://via.placeholder.com/150",
        },
        {
          id: 3,
          title: "Похожее объявление 2",
          price: 12000,
          image: "https://via.placeholder.com/150",
        },
        {
          id: 4,
          title: "Похожее объявление 3",
          price: 8000,
          image: "https://via.placeholder.com/150",
        },
      ],
    };
  },
  methods: {
    formatPrice(value) {
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ' ');
    },
    viewDetails() {
      this.$router.push({ name: 'adDetails', params: { id: this.ad.id } });
    },
    showContact() {
      this.contactDialog = true;
    },
    viewSellerAds() {
      this.$router.push({ name: 'sellerAds', params: { seller: this.ad.seller.name } });
    },
    toggleFavorite() {
      this.isFavorite = !this.isFavorite;
    },
    viewSimilarAd(id) {
      this.$router.push({ name: 'adDetails', params: { id } });
    },
  },
};
</script>

<style scoped>
.ad-card {
  margin-bottom: 40px;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.headline {
  font-weight: bold;
  font-size: 1.5rem;
}

.category, .location, .meta-info, .seller-info {
  font-size: 1.2rem;
  color: gray;
}

.price {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 15px;
}

v-img {
  border-radius: 8px;
  object-fit: contain;
}

v-btn {
  margin-top: 20px;
}

.similar-ads {
  margin-top: 40px;
}

.similar-ad-card {
  padding: 10px;
  text-align: center;
}

.similar-ad-card .headline {
  font-size: 1.2rem;
  font-weight: bold;
}

.similar-ad-card .price {
  font-size: 1.2rem;
  color: gray;
}

.similar-ad-card v-btn {
  margin-top: 10px;
}
</style>
