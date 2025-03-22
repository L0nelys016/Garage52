package com.example.garage52develop.Domain

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object Constant {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://ncuslkyybzdatgwwbtli.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im5jdXNsa3l5YnpkYXRnd3didGxpIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDE2OTEyMDksImV4cCI6MjA1NzI2NzIwOX0.sYAYl1NDwYQ0qft7o-AubUeXtDlYvWnM8dpvst2IY8s"
    ) {
        install(Postgrest)
        install(Auth)
    }
}