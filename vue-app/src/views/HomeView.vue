<template>
  <div class="container">
    <header class="header">
      <div class="nav-container">
        <nav class="navigation">
          <a class="nav-link" href="#">Home</a>
          <a class="nav-link" href="#">Projects</a>
          <a class="nav-link" href="#">Contact</a>
        </nav>
      </div>
    </header>
    <main class="main-content">
      <div class="text-container">
        <div class="text-section">
          <p class="welcome-text">
            Welcome to the world of
          </p>
          <h1 class="main-title">
            LANI & JJINKO
          </h1>
        </div>
        <div class="description-container">
          <p class="description-text">
            We create beautiful memories. Let us capture your moments.
          </p>
        </div>
      </div>
    </main>
    <footer class="footer">
      <div class="footer-container">
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'MainPage',
};
</script>

<style>

body{
  margin: 0;
}
.container {
  min-height: 100vh; /* 이 부분을 추가합니다 */
  display: flex;
  flex-direction: column; /* 자식 요소들을 세로로 정렬합니다 */
  background-color: #fff;
  border-bottom: 1px solid #e2e8f0;
  --tw-bg-opacity: 1;
  background-color: rgba(31, 41, 55, var(--tw-bg-opacity));
}

/* 나머지 스타일은 유지합니다 */
.header {
  padding: 1rem 0;
}

.nav-container {
  display: flex;
  justify-content: space-between;
  padding: 0 1rem;
}

.navigation .nav-link {
  margin-right: 1rem;
  font-weight: 500;
  color: transparent;
  border-bottom: 2px solid transparent;
  transition: color 0.3s;
}

.navigation .nav-link:hover {
  border-color: #1f2937;
}

.main-content {
  flex-grow: 1; /* 메인 콘텐츠가 남은 공간을 모두 차지하도록 합니다 */
  padding: 1.5rem 0;
}

.text-container {
  text-align: center;
}

.text-section .welcome-text {
  color: #6b7280;
  font-weight: 600;
}

.text-section .main-title {
  font-size: 2.25rem;
  font-weight: 800;
}

.description-container .description-text {
  color: #6b7280;
}

.footer {
  padding: 3rem 0;
}

.footer-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
}

.footer-links {
  display: flex;
  gap: 1rem;
}

.footer-link {
  font-weight: 500;
}

.socials-avatar-container .avatar-link .avatar-image {
  border-radius: 9999px;
  aspect-ratio: 1;
  object-fit: cover;
}
</style>
