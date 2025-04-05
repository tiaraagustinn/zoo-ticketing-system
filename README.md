# Zoo Ticketing System

Sistem tiket kebun binatang yang dibangun menggunakan pendekatan **Component-Based Software Engineering (CBSE)** dengan penerapan konsep *provided* dan *required interface*.

## Deskripsi

Aplikasi ini merupakan implementasi sistem pembelian dan validasi tiket untuk kebun binatang. Fitur utama dari aplikasi ini meliputi:

- Pembelian tiket sesuai kategori (dewasa, anak-anak, senior)
- Pembuatan ID tiket secara otomatis
- Validasi tiket di pintu masuk
- Pencatatan dan pengecekan data tiket untuk mencegah duplikasi

Sistem ini dirancang menggunakan prinsip CBSE dengan pendekatan *Design by Contract*, mengandalkan interaksi antar komponen melalui antarmuka (interfaces) yang jelas dan terdefinisi.

## Struktur Komponen

Diagram sistem terdiri dari 5 komponen utama dan 4 antarmuka sebagai penghubung antar komponen:

### Komponen

#### 1. `TicketBooth`
- **Provided Interface:** `PurchaseInterface`, `TicketInterface`
- Berfungsi untuk menangani proses pembelian tiket dan pencatatan data tiket yang valid.

#### 2. `ZooEntrance`
- **Provided Interface:** `ValidateInterface`
- **Required Interface:** `TicketInterface`
- Bertugas untuk memvalidasi tiket dengan mengakses data dari `TicketBooth`.

#### 3. `PurchaseButton`
- **Provided Interface:** `UserInterface`
- **Required Interface:** `PurchaseInterface`
- Menghubungkan pengguna ke proses pembelian tiket.

#### 4. `ValidateButton`
- **Provided Interface:** `UserInterface`
- **Required Interface:** `ValidateInterface`
- Menghubungkan pengguna ke proses validasi tiket.

#### 5. `ZooTicketingSystem`
- **Required Interfaces:** `PurchaseInterface`, `ValidateInterface`, `TicketInterface`
- Sebagai komponen pusat sistem yang mengkoordinasikan proses pembelian dan validasi tiket melalui komponen lain.

## Antarmuka (Interface)

Berikut adalah antarmuka (contracts) yang digunakan antar komponen:

### `PurchaseInterface`

```java
public interface PurchaseInterface {
    String purchaseTicket(String category, int quantity);
}
```

Digunakan oleh `PurchaseButton` untuk memproses pembelian tiket melalui `TicketBooth`.

### `ValidateInterface`

```java
public interface ValidateInterface {
    boolean validateTicket(String ticketID);
}
```
Digunakan oleh `ValidateButton` untuk memvalidasi tiket melalui `ZooEntrance`.

### `TicketInterface`

```java
public interface TicketInterface {
    boolean isTicketValid(String ticketID);
    void registerTicket(String ticketID);
}
```
Digunakan oleh `ZooEntrance` untuk memverifikasi data tiket dari `TicketBooth`.

### `UserInterface`

```java
public interface UserInterface {
    void buttonPressed();
}
```
Antarmuka umum untuk tombol yang digunakan oleh pengguna (`PurchaseButton` dan `ValidateButton`).

## Hubungan dan Ketergantungan

- `PurchaseButton` membutuhkan `PurchaseInterface` dari `TicketBooth`.
- `ValidateButton` membutuhkan `ValidateInterface` dari `ZooEntrance`.
- `ZooEntrance` membutuhkan `TicketInterface` dari `TicketBooth`.
- `ZooTicketingSystem` membutuhkan semua antarmuka utama untuk menjalankan sistem.

## Cara Menjalankan Aplikasi

### Prasyarat
- Java Development Kit (JDK) 8 atau lebih tinggi
- IDE seperti IntelliJ IDEA, Eclipse, atau gunakan command line

### Langkah-langkah
1. Clone repository:
   ```bash
   git clone https://github.com/your-username/zoo-ticketing-system.git
   cd zoo-ticketing-system
   ```

2. Kompilasi semua file Java:
   ```bash
   javac *.java
   ```

3. Jalankan aplikasi:
   ```bash
   java ZooTicketingSystem
   ```

4. Ikuti petunjuk pada konsol untuk melakukan pembelian dan validasi tiket.

## Fitur

### 1. Pembelian Tiket
- Pilih kategori (dewasa, anak, senior)
- Masukkan jumlah tiket
- Dapatkan ID tiket unik
- Tiket disimpan dalam sistem

### 2. Validasi Tiket
- Masukkan ID tiket
- Sistem memeriksa validitas dan status tiket
- Tiket hanya bisa digunakan sekali

### 3. Pencegahan Duplikasi
- Sistem mencatat semua tiket yang telah digunakan
- Tiket yang sama tidak bisa digunakan lebih dari sekali

## Konsep Design by Contract

Sistem ini menggunakan pendekatan Design by Contract yang mencakup:

- **Provided Interface:** Kontrak layanan yang ditawarkan oleh suatu komponen
- **Required Interface:** Kontrak layanan yang dibutuhkan dari komponen lain
- **Preconditions:** Syarat yang harus dipenuhi sebelum operasi dilakukan (misalnya, tiket harus sudah terdaftar)
- **Postconditions:** Jaminan hasil setelah operasi selesai (misalnya, tiket valid memberi akses masuk)

## Pengembangan Selanjutnya

Beberapa ide pengembangan lebih lanjut:

- Penyimpanan data tiket secara permanen (misalnya ke dalam file atau database)
- GUI berbasis JavaFX atau Swing
- Sistem notifikasi untuk tiket yang sudah hampir habis
- Laporan statistik pengunjung berdasarkan kategori

## Anggota Tim
- Andika Pebriansyah – 2208107010058
- Tiara Agustin – 2208107010004
